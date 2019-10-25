package com.obss.spring.MovieApp.controller.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDirectorToMovieRequest {
	
	@NotNull
	private Long directorId;
	
	@NotNull
	private Long movieId;
	
}
