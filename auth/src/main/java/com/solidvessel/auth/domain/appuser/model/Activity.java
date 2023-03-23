package com.solidvessel.auth.domain.appuser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Activity {

    private boolean isActive;
    private String deactivationReason;
}
