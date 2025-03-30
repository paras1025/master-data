package com.master.util;

import com.master.exception.MastersValidationException;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class MastersValidator {

    public static void validateMandatoryFields(Object object) throws IllegalAccessException,
            MastersValidationException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MastersMandatory.class)) {
                validateMastersMandatory(object, field);
            }
            if (field.isAnnotationPresent(SizeConstraint.class)) {
                validateSizeConstraints(object, field);
            }
            if (isCollectionFieldWithAnnotatedElement(field, MastersMandatory.class)) {
                validateNestedMastersMandatory(object, field);
            }
        }
    }

    private static void validateMastersMandatory(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(object);
        MastersMandatory mandatory = field.getAnnotation(MastersMandatory.class);
        String message = mandatory.message().isEmpty() ? String.format("%s is a mandatory field", field.getName()) :
                mandatory.message();
        validateMastersMandatory(value, message);
    }

    private static void validateNestedMastersMandatory(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(object);
        String message = field.getName() + " can not have null or empty values";
        if (value instanceof Collection) {
            for (Object item : (Collection<?>) value) {
                validateMastersMandatory(item, message);
            }
        }
    }

    private static void validateMastersMandatory(Object value, String message) {
        if (isNull(value) || isEmpty(value)) {
            throwException(message);
        }
    }

    private static boolean isEmpty(Object value) {
        return (isEmptyString(value) || isEmptyCollection(value) || isEmptyMap(value));
    }

    private static boolean isEmptyString(Object value) {
        return value instanceof String && StringUtils.isBlank((String) value);
    }

    private static boolean isEmptyCollection(Object value) {
        return value instanceof Collection && ((Collection<?>) value).isEmpty();
    }

    private static boolean isEmptyMap(Object value) {
        return value instanceof Map && ((Map<?, ?>) value).isEmpty();
    }

    private static void validateSizeConstraints(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(object);
        SizeConstraint sizeConstraint = field.getAnnotation(SizeConstraint.class);
        if (isNull(value) && sizeConstraint.mandatory()) {
            throwException(sizeConstraint.message().isEmpty() ?
                    String.format("[%s] is mandatory and cannot be null", field.getName()) :
                    sizeConstraint.message());
        }
        validateObjectSize(value, sizeConstraint.min(), sizeConstraint.max(),
                getSizeValidationMessage(sizeConstraint, field.getName()));

    }

    private static void validateObjectSize(Object value, int min, int max, String message) {
        if (isInvalidStringSize(value, min, max)
                || isInvalidCollectionSize(value, min, max)
                || isInvalidMapSize(value, min, max)) {
            throwException(message);
        }
    }

    private static boolean isInvalidStringSize(Object value, int min, int max) {
        return value instanceof String && (((String) value).length() < min
                || ((String) value).length() > max);
    }

    private static boolean isInvalidCollectionSize(Object value, int min, int max) {
        return value instanceof Collection<?> && (((Collection<?>) value).size() < min
                || ((Collection<?>) value).size() > max);
    }

    private static boolean isInvalidMapSize(Object value, int min, int max) {
        return value instanceof Map<?, ?> && (((Map<?, ?>) value).size() < min
                || ((Map<?, ?>) value).size() > max);
    }

    private static boolean isNull(Object value) {
        return value == null;
    }

    private static String getSizeValidationMessage(SizeConstraint sizeConstraint,
                                                   String fieldName) {
        return sizeConstraint.message().isEmpty() ? String.format(
                "field [%s] size should be between %s and %s", fieldName,
                sizeConstraint.min(), sizeConstraint.max()) :
                sizeConstraint.message();
    }

    private static void throwException(String message) {
        throw new MastersValidationException(message);
    }

    private static boolean isCollectionFieldWithAnnotatedElement(Field field,
                                                                 Class<? extends Annotation> annotationClass) {
        return Collection.class.isAssignableFrom(field.getType()) && isAnnotated(field, annotationClass);
    }

    private static boolean isAnnotated(Field field, Class<? extends Annotation> annotationClass) {
        boolean isAnnotated = false;
        AnnotatedType annotatedType = field.getAnnotatedType();
        if (annotatedType instanceof AnnotatedParameterizedType parameterizedType) {
            AnnotatedType[] annotatedArguments = parameterizedType.getAnnotatedActualTypeArguments();
            isAnnotated = annotatedArguments.length > 0 && annotatedArguments[0].isAnnotationPresent(annotationClass);
        }
        return isAnnotated;
    }
}
