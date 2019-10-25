package com.obss.spring.MovieApp.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.obss.spring.MovieApp.model.Directors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsDtosToMovies implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long directorId;

	private String name;

	private Date birthdate;

	private String birthplace;

	private List<MoviesDtos> moviesDtos;

	public DirectorsDtosToMovies(Directors directors) {
    	this.directorId = directors.getDirectorId();
    	this.name = directors.getName();
    	this.birthdate = directors.getBirthdate();
    	this.birthplace = directors.getBirthplace();
    	this.moviesDtos = directors.getMoviesDtos();
    }
}
