package com.solidvessel.auth.infra.adapter.appuser.db.entity;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AppUserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Embedded
    private ActivityEmbeddable activity;

    public AppUser toDomainModel() {
        return new AppUser(id, email, password, activity.toValueObject());
    }

    public static AppUserJpaEntity from(AppUser appUser) {
        return new AppUserJpaEntity(
                appUser.getId(),
                appUser.getEmail(),
                appUser.getPassword(),
                ActivityEmbeddable.from(appUser.getActivity())
        );
    }
}
