package com.liberbox.user.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import com.liberbox.user.domain.enums.Profile;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

	@EqualsAndHashCode.Include
	@Id
	private String id;
	private String email;
	private String password;
	private String nickname;
	private Boolean active;
	private String photo;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profiles")
	protected Set<Integer> profile = new HashSet<>();

	private User(String email, String password, String nickname, String photo) {
		this.id = UUID.randomUUID().toString();
		this.active = Boolean.TRUE;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.photo = photo;
	}

	public static User to(String email, String password, String nickname, String photo) {
		return new User(email, password, nickname, photo);
	}

	public User disable() {
		this.active = Boolean.FALSE;
		return this;
	}

	public User toUpdate(String nickname, String photo) {
		this.nickname = nickname;
		this.photo = photo;
		return this;
	}

	public User toUpdatePassword(String newPassword) {
		this.password = newPassword;
		return this;
	}

	public Set<Profile> getPerfis() {
		return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Profile perfil) {
		this.profile.add(perfil.getCode());
	}

}
