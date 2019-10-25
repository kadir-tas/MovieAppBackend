package com.obss.spring.MovieApp.model.dto;

import java.io.Serializable;
import java.util.List;

import com.obss.spring.MovieApp.model.Movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesDtosToDirectors implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long movieId;
	
	private String name;

	private int releasedate;

	private double imdbRating;

	private int duration;
	
	private String genre;
	
	private List<DirectorsDtos> directorsDtos;
	
	public MoviesDtosToDirectors(Movies movie) {
		this.movieId = movie.getMovieId();
		this.name = movie.getName();
		this.releasedate = movie.getReleasedate();
		this.imdbRating = movie.getImdbRating();
		this.duration = movie.getDuration();
		this.genre = movie.getGenre();
		this.directorsDtos = movie.getDirectorsDtos();
	}
}
