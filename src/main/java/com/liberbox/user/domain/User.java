package com.liberbox.user.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

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

}
