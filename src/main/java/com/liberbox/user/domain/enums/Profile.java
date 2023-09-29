package com.liberbox.user.domain.enums;

public enum Profile {

	USER(0, "ROLE_USER"), ADMIN(1, "ROLE_ADMIN");

	private Integer code;
	private String description;

	private Profile(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Profile toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Profile x : Profile.values()) {
			if (cod.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Perfil inválido");
	}
}
