package io.dronefleet.mavlink.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.lang.model.element.Modifier;

public class FieldGenerator implements Comparable<FieldGenerator> {
    private static final ClassName MAVLINK_MESSAGE_FIELD = ClassName.get(
            "io.dronefleet.mavlink.annotations",
            "MavlinkFieldInfo");
    private static final ClassName ENUM_VALUE = ClassName.get(
            "io.dronefleet.mavlink.util",
            "EnumValue");
    private static final ClassName SERIALIZATION_HELPER = ClassName.get(
            "io.dronefleet.mavlink.serialization.payload",
            "PayloadFieldDecoder"
    );

    private final PackageGenerator parentPackage;
    private final String name;
    private final String nameCamelCase;
    private final String description;
    private final String type;
    private final String enumName;
    private final int index;
    private final int unitSize;
    private final boolean array;
    private final int arraySize;
    private final boolean extension;

    public FieldGenerator(
            PackageGenerator parentPackage,
            String name,
            String nameCamelCase,
            String description,
            String type,
            String enumName,
            int index,
            int unitSize,
            boolean array,
            int arraySize,
            boolean extension) {
        this.parentPackage = parentPackage;
        this.name = name;
        this.nameCamelCase = nameCamelCase;
        this.description = description;
        this.type = type;
        this.enumName = enumName;
        this.index = index;
        this.unitSize = unitSize;
        this.array = array;
        this.arraySize = arraySize;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isArray() {
        return array;
    }

    public int getArraySize() {
        return arraySize;
    }

    public boolean isExtension() {
        return extension;
    }

    public TypeName javaType() {
        if (enumName != null) {
            return enumValueType();
        }
        if (array) {
            return arrayType();
        }
        return primitiveType();
    }

    public String getNameCamelCase() {
        return nameCamelCase;
    }

    public String javadoc() {
        return parentPackage.processJavadoc(description);
    }

    public AnnotationSpec annotation() {
        AnnotationSpec.Builder builder = AnnotationSpec.builder(MAVLINK_MESSAGE_FIELD)
                .addMember("position", "$L", index)
                .addMember("unitSize", "$L", unitSize);

        if (array) {
            builder.addMember("arraySize", "$L", arraySize);
        }

        if (enumName != null) {
            builder.addMember("enumType", "$T.class", enumType());
        }

        if (signed()) {
            builder.addMember("signed", "$L", true);
        }

        if (extension) {
            builder.addMember("extension", "$L", true);
        }

        if (description != null && !description.trim().isEmpty()) {
            builder.addMember("description", "$S", description);
        }

        return builder.build();
    }

    public FieldSpec generateImmutableMember() {
        return FieldSpec.builder(javaType(), nameCamelCase, Modifier.PRIVATE, Modifier.FINAL).build();
    }

    public FieldSpec generateMutableMember() {
        return FieldSpec.builder(javaType(), nameCamelCase, Modifier.PRIVATE).build();
    }

    public MethodSpec generateGetter() {
        return MethodSpec.methodBuilder(nameCamelCase)
                .addJavadoc(javadoc())
                .addAnnotation(annotation())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addStatement("return this.$N", nameCamelCase)
                .returns(javaType())
                .build();
    }

    public MethodSpec generateSetter(ClassName className) {
        return MethodSpec.methodBuilder(nameCamelCase)
                .addJavadoc(javadoc())
                .addAnnotation(annotation())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addParameter(generateParameter())
                .addStatement("this.$1N = $1N", nameCamelCase)
                .addStatement("return this")
                .returns(className)
                .build();
    }

    public List<MethodSpec> generateConvenienceSetters(ClassName className) {
        if (enumName != null) {
            return Arrays.asList(
                    MethodSpec.methodBuilder(nameCamelCase)
                            .addJavadoc(javadoc())
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .addParameter(enumType(), "entry")
                            .addStatement("return $1N($2T.of($3N))", nameCamelCase, ENUM_VALUE, "entry")
                            .returns(className)
                            .build(),

                    MethodSpec.methodBuilder(nameCamelCase)
                            .varargs(true)
                            .addJavadoc(javadoc())
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .addParameter(Enum[].class, "flags")
                            .addStatement("return $1N($2T.create(flags))", nameCamelCase, ENUM_VALUE)
                            .returns(className)
                            .build(),

                    MethodSpec.methodBuilder(nameCamelCase)
                            .addJavadoc(javadoc())
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .addParameter(ParameterizedTypeName.get(Collection.class, Enum.class), "flags")
                            .addStatement("return $1N($2T.create(flags))", nameCamelCase, ENUM_VALUE)
                            .returns(className)
                            .build()
            );
        }
        return Collections.emptyList();
    }

    public ParameterSpec generateParameter() {
        return ParameterSpec.builder(javaType(), nameCamelCase).build();
    }

    public void addEqualsStatement(CodeBlock.Builder codeBuilder, String other) {
        codeBuilder.addStatement("if (!$1T.deepEquals($2N, $3N.$2N)) return false",
                Objects.class,
                getNameCamelCase(),
                other);
    }

    public void addHashCodeStatement(CodeBlock.Builder codeBuilder, String result) {
        codeBuilder.addStatement("$1N = 31 * $1N + $2T.hashCode($3N)",
                result,
                Objects.class,
                getNameCamelCase());
    }


    private static String mavlinkToTitleCaseJavaPrimitiveTypeName(String typeName){
        if (typeName.endsWith("_t"))
            typeName = typeName.substring(0, typeName.length()-2);
        return Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1); // Title case
    }

    private String decodeMethodName(){
        String simpleTypeName;
        if (enumName != null){
            simpleTypeName = "Enum";
        }
        else if (array){
            if ("char".equals(type))
                simpleTypeName = "String";
            else
                simpleTypeName = mavlinkToTitleCaseJavaPrimitiveTypeName(type) + "Array";
        }
        else
            simpleTypeName = mavlinkToTitleCaseJavaPrimitiveTypeName(type);

        return "decode" + simpleTypeName;
    }

    public void addDeserializationStatement(CodeBlock.Builder codeBuilder, String inputName){
        String decodeMethodName = decodeMethodName();
        int length = unitSize * Math.max(arraySize, 1);
        if (enumName != null)
            codeBuilder.addStatement("$T $N = $T.$L($L, $L, $L)", javaType(), nameCamelCase, SERIALIZATION_HELPER, decodeMethodName, enumType() + ".class"  , inputName, length);
        else if (array)
            codeBuilder.addStatement("$T $N = $T.$L($L, $L)", javaType(), nameCamelCase, SERIALIZATION_HELPER, decodeMethodName, inputName, length);
        else
            codeBuilder.addStatement("$T $N = $T.$L($L)", javaType(), nameCamelCase, SERIALIZATION_HELPER, decodeMethodName, inputName);
    }

    private boolean signed() {
        return type.startsWith("int");
    }

    private TypeName enumType() {
        return parentPackage.resolveEnum(enumName)
                .map(EnumGenerator::getClassName)
                .orElseThrow(() -> new IllegalStateException("unable to find enum " + enumName
                        + " while generating field " + name));
    }

    private TypeName enumValueType() {
        return ParameterizedTypeName.get(ENUM_VALUE, enumType());
    }

    private final TypeName arrayType() {
        if ("char".equals(type)) {
            return ClassName.get(String.class);
        }
        if ("uint8_t".equals(type)) {
            return TypeName.get(byte[].class);
        }
        return ParameterizedTypeName.get(ClassName.get(List.class), primitiveType().box());
    }

    private TypeName primitiveType() {
        switch (type) {
            case "uint8_t":
            case "int8_t":
            case "uint16_t":
            case "int16_t":
            case "int32_t":
                return TypeName.INT;

            case "uint32_t":
            case "int64_t":
                return TypeName.LONG;

            case "uint64_t":
                return ClassName.get(BigInteger.class);

            case "float":
                return TypeName.FLOAT;

            case "double":
                return TypeName.DOUBLE;
        }

        return TypeName.INT;
    }

    @Override
    public int compareTo(FieldGenerator other) {
        if (extension && !other.extension) {
            return 1;
        }
        if (!extension && other.extension) {
            return -1;
        }

        if (!this.extension && this.unitSize != other.unitSize) {
            return other.unitSize - unitSize;
        }

        return index - other.index;
    }
}
