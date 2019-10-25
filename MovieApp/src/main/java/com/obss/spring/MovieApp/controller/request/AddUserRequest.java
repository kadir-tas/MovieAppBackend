package com.obss.spring.MovieApp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
	
	private String username;

	private String name;

	private String lastname;

	private String password;

	private String email;

	private String authority;
	
}
