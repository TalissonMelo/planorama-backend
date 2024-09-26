package com.liberbox.user.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import com.liberbox.audit.repository.Auditable;
import com.liberbox.config.domain.ToEntity;
import com.liberbox.user.domain.enums.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class User extends ToEntity implements Auditable {

    @Id
    private String id;

    @Column(unique = true)
    private String email;
    private String password;
    private String nickname;
    private Boolean active;
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userTokens")
    protected Set<String> userTokens = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profiles")
    protected Set<Integer> profile = new HashSet<>();

    private User(String email, String password, String nickname, String phone) {
        this.id = UUID.randomUUID().toString();
        this.active = Boolean.TRUE;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
    }

    public static User to(String email, String password, String nickname, String phone) {
        return new User(email, password, nickname, phone);
    }

    public User disabled() {
        this.active = Boolean.FALSE;
        return this;
    }

    public User toUpdated(String nickname, String phone) {
        this.nickname = nickname;
        this.phone = phone;
        return this;
    }

    public User toUpdatedPassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    public Set<Profile> getProfiles() {
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getCode());
    }

    @Override
    public String getEntityId() {
        return this.id;
    }

    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }

    public void addProfile(String token) {

        this.userTokens.add(token);
    }

}
