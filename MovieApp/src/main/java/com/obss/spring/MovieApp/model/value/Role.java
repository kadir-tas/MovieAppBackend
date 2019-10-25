package com.obss.spring.MovieApp.model.value;

public enum Role {

	ROLE_ADMIN("ADMIN"),

	ROLE_USER("USER");

	private final String role;

	private Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}
}
