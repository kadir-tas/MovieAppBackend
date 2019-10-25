package com.obss.spring.MovieApp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {

	private String username;
	
	private String authority;
	
}
