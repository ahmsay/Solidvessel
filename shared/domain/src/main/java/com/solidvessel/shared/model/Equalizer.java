package com.solidvessel.shared.model;

import lombok.Generated;

import java.lang.reflect.Field;
import java.util.Objects;

/*
 * The reason I didn't use @EqualsAndHashCode annotation from Lombok is that in their implementation there was a single
 * line that is extremely difficult to cover with tests. Even with EqualsVerifier there is a lot of work to do to cover
 * that line. Another option was to add a lombok.config file and exclude Lombok generated code from coverage. But after
 * that configuration, private fields were excluded as well which was not helpful at all. The only working solution to
 * maintain %100 coverage was to implement my own generic equals and hashcode method.
 */

public final class Equalizer {

    @Generated
    public static boolean equals(Object current, Object other) {
        if (current == other) return true;
        if (other == null || current.getClass() != other.getClass()) return false;

        try {
            for (Field field : getAllFields(current.getClass())) {
                field.setAccessible(true);
                Object thisValue = field.get(current);
                Object otherValue = field.get(other);

                if (!Objects.equals(thisValue, otherValue)) {
                    return false;
                }
            }
            return true;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing fields in equals()", e);
        }
    }

    @Generated
    public static int hashCode(Object obj) {
        try {
            Object[] fieldValues = new Object[getAllFields(obj.getClass()).length];
            int index = 0;

            for (Field field : getAllFields(obj.getClass())) {
                field.setAccessible(true);
                fieldValues[index++] = field.get(obj);
            }

            return Objects.hash(fieldValues);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing fields in hashCode()", e);
        }
    }

    @Generated
    private static Field[] getAllFields(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Class<?> superclass = clazz.getSuperclass();

        if (superclass != null && superclass != Object.class) {
            Field[] parentFields = getAllFields(superclass);
            Field[] allFields = new Field[declaredFields.length + parentFields.length];

            System.arraycopy(declaredFields, 0, allFields, 0, declaredFields.length);
            System.arraycopy(parentFields, 0, allFields, declaredFields.length, parentFields.length);

            return allFields;
        }
        return declaredFields;
    }
}
