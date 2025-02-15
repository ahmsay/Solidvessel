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
public abstract class DomainModel implements Serializable {

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
            for (Field field : this.getClass().getDeclaredFields()) {
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
            Object[] fieldValues = new Object[this.getClass().getDeclaredFields().length];
            int index = 0;

            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                fieldValues[index++] = field.get(this);
            }

            return Objects.hash(fieldValues);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing fields in hashCode()", e);
        }
    }
}
