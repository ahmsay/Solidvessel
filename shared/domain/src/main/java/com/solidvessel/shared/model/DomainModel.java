package com.solidvessel.shared.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
public class DomainModel implements Serializable {

    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer version;
}
