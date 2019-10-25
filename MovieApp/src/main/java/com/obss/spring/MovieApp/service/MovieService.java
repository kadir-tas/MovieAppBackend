package com.obss.spring.MovieApp.service;

import java.net.MalformedURLException;
import java.util.List;

import com.obss.spring.MovieApp.controller.request.AddMovieRequest;
import com.obss.spring.MovieApp.controller.request.SearchImdbRequest;
import com.obss.spring.MovieApp.model.Movies;
import com.obss.spring.MovieApp.model.dto.DirectorsDtosToMovies;
import com.obss.spring.MovieApp.model.dto.MoviesDtosToDirectors;

public interface MovieService {
	
	public Object addMovieWithName(String string) throws MalformedURLException;
	
	public boolean addMovie(AddMovieRequest addMovieRequest);

	public boolean updateMovie(Movies movie);

	public boolean deleteMovie(Long movieId);

	public MoviesDtosToDirectors getMovieById(Long movieId);

	public List<MoviesDtosToDirectors> getAllMovies();

	public MoviesDtosToDirectors getMovieByName(String movieName);

	public boolean addMovieDirectorsById(Long directorsId, Long movieId);

	public List<DirectorsDtosToMovies> getDirectorsAllMoviesWithName(String name);


}
