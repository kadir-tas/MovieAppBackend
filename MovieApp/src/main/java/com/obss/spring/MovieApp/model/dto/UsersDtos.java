package com.obss.spring.MovieApp.model.dto;

import java.io.Serializable;
import java.util.List;

import com.obss.spring.MovieApp.model.Users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UsersDtos implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String username;

	private String name;
	
	private String lastname;
	
	private String email;	
	
	private List<AuthoritiesDtos> authoritiesDtos;
	
	public UsersDtos(Users users) {
		this.username = users.getUsername();
		this.email = users.getEmail();
		this.userId = users.getUserId();
		this.lastname = users.getLastname();
		this.name = users.getName();		
		this.authoritiesDtos = users.getAuthoritiesDtos();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AuthoritiesDtos> getAuthoritiesDtos() {
		return authoritiesDtos;
	}

	public void setAuthoritiesDtos(List<AuthoritiesDtos> authoritiesDtos) {
		this.authoritiesDtos = authoritiesDtos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
