package com.obss.spring.MovieApp.controller.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
    
}
