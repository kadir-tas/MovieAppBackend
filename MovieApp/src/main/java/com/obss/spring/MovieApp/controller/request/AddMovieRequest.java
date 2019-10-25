package com.obss.spring.MovieApp.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMovieRequest {
	
	@NotNull
	private String name;
	
	@NotEmpty
	private int releasedate;

	@NotNull
	private String genre;
	
	private String imdbId;
	
	private double imdbRating;

	private int duration;
		
	//these variables for assigning director and also they are optional(nullable)
	private String directorName;
	
	private Long directorId;

		
}
