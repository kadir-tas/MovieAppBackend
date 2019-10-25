package com.obss.spring.MovieApp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMovieFromImdb {
	
	private String name;
	
	private int releasedate;

	private double imdbRating;

	private int duration;
	
	private String genre;	
	
	private String directorName;
		
}
