package com.obss.spring.MovieApp.model.dto;

import java.io.Serializable;

import com.obss.spring.MovieApp.model.Authorities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesDtos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String authority;
	
	public AuthoritiesDtos(String authority) {
		this.authority = authority;
	}
	
	public AuthoritiesDtos(Authorities authorities) {
		this.authority = authorities.getAuthority();
		this.id = authorities.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
