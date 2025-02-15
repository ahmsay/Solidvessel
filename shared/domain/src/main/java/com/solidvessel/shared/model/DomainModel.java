package com.solidvessel.shared.model;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@SuperBuilder
@NoArgsConstructor
public class DomainModel implements Serializable {

    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer version;

    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        try {
            for (Field field : getAllFields(this.getClass())) {
                field.setAccessible(true);
                Object thisValue = field.get(this);
                Object otherValue = field.get(obj);

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
    @Override
    public int hashCode() {
        try {
            Object[] fieldValues = new Object[getAllFields(this.getClass()).length];
            int index = 0;

            for (Field field : getAllFields(this.getClass())) {
                field.setAccessible(true);
                fieldValues[index++] = field.get(this);
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
