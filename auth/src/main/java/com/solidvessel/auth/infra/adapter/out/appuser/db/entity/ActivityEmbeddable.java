package com.solidvessel.auth.infra.adapter.out.appuser.db.entity;

import com.solidvessel.auth.domain.appuser.model.Activity;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActivityEmbeddable {

    private boolean isActive;
    private String deactivationReason;

    public static ActivityEmbeddable from(Activity activity) {
        return new ActivityEmbeddable(activity.isActive(), activity.getDeactivationReason());
    }

    public Activity toValueObject() {
        return new Activity(isActive, deactivationReason);
    }
}
