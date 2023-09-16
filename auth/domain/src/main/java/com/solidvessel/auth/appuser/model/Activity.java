package com.solidvessel.auth.appuser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Activity {

    private boolean isActive;
    private String deactivationReason;
}
