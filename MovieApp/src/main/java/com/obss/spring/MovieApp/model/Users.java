package com.obss.spring.MovieApp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;

import com.obss.spring.MovieApp.controller.request.AddUserRequest;
import com.obss.spring.MovieApp.model.dto.AuthoritiesDtos;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(unique = true)
	@NotEmpty(message = "*Please provide name")
	private String username;

	@Column
	@NotEmpty
	private String name;

	@Column
	@NotEmpty
	private String lastname;

	@Column(name = "password", nullable = false)
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
	private Set<Authorities> authorities = new HashSet<>();

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	public Users(AddUserRequest addUserRequest) {
		this.username = addUserRequest.getUsername();
		this.password = addUserRequest.getPassword();
		this.email = addUserRequest.getEmail();
		this.lastname = addUserRequest.getLastname();
		this.name = addUserRequest.getName();
	}
	
	public List<AuthoritiesDtos> getAuthoritiesDtos(){
		List<AuthoritiesDtos> authoritiesDtos = new ArrayList<>();
		for(Authorities a : authorities) {
			authoritiesDtos.add(new AuthoritiesDtos(a));
		}
		return authoritiesDtos;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}
	
}