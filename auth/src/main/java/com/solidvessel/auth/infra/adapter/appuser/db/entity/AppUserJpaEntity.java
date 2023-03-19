package com.solidvessel.auth.infra.adapter.appuser.db.entity;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String username;

    @NotNull
    private String password;

    public AppUserJpaEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser toDomainModel() {
        return new AppUser(id, username, password);
    }

    public static AppUserJpaEntity from(AppUser appUser) {
        return new AppUserJpaEntity(appUser.getUsername(), appUser.getPassword());
    }
}
