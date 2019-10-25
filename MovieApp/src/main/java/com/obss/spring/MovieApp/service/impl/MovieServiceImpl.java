package com.obss.spring.MovieApp.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.spring.MovieApp.config.OmdbConnection;
import com.obss.spring.MovieApp.controller.request.AddMovieFromImdb;
import com.obss.spring.MovieApp.controller.request.AddMovieRequest;
import com.obss.spring.MovieApp.controller.request.SearchImdbRequest;
import com.obss.spring.MovieApp.model.Directors;
import com.obss.spring.MovieApp.model.Message;
import com.obss.spring.MovieApp.model.Movies;
import com.obss.spring.MovieApp.model.dto.DirectorsDtosToMovies;
import com.obss.spring.MovieApp.model.dto.MoviesDtosToDirectors;
import com.obss.spring.MovieApp.repository.DirectorRepository;
import com.obss.spring.MovieApp.repository.MovieRepository;
import com.obss.spring.MovieApp.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	DirectorRepository directorRepository;

	@Override
	public Object addMovieWithName(String movieName) throws MalformedURLException {
		Object obj = OmdbConnection.getMovieInfo(movieName);
		if (obj instanceof Message) {
			return obj;
		} else {
			AddMovieFromImdb addMovieFromImdb = (AddMovieFromImdb) obj;

			if (!movieRepository.existsByNameAndDirectorsName(addMovieFromImdb.getName(),
					addMovieFromImdb.getDirectorName())) {
				return addMovieFromImdb;
			} else {
				return new Message("Movie already exist!");
			}
		}
	}

	@Override
	public boolean updateMovie(Movies movie) {

		if (!movieRepository.existsById(movie.getMovieId())) {
			return false;
		} else {
			movieRepository.save(movie);
			return true;
		}
	}

	@Override
	public boolean deleteMovie(Long movieId) {

		if (movieRepository.existsById(movieId)) {
			movieRepository.delete(movieRepository.getOne(movieId));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MoviesDtosToDirectors getMovieById(Long movieId) {
		if (movieRepository.existsById(movieId)) {
			return new MoviesDtosToDirectors(movieRepository.findById(movieId).get());
		}
		return null;
	}

	@Override
	public List<MoviesDtosToDirectors> getAllMovies() {
		List<MoviesDtosToDirectors> moviesDtosToDirectors = new ArrayList<>();
		for (Movies m : movieRepository.findAll()) {
			moviesDtosToDirectors.add(new MoviesDtosToDirectors(m));
		}
		return moviesDtosToDirectors;
	}

	@Override
	public MoviesDtosToDirectors getMovieByName(String movieName) {
		return new MoviesDtosToDirectors(movieRepository.findByName(movieName));
	}

	// If there is a name and surname of director,then this method will add the
	// director to movie
	public void addMovieDirectorsByName(String directorname, Movies movie) {
		if (directorname != null) {
			String[] directorInfo = directorname.split(" ");
			int len = directorInfo.length;
			if (len >= 2) {
				Directors director = directorRepository.findByName(directorname);
				if (director == null) {
					director = new Directors(directorname, "default");
				}
				if (movie.getDirectors() == null) {
					List<Directors> directors = new ArrayList<>();
					movie.setDirectors(directors);
				}
				List<Directors> putDirectors = movie.getDirectors();
				putDirectors.add(director);
				movie.setDirectors(putDirectors);
			}
		}
	}

	@Override
	public boolean addMovieDirectorsById(Long directorsId, Long movieId) {
		if (directorRepository.existsById(directorsId)) {
			Movies movie = movieRepository.getOne(movieId);
			movie.getDirectors().add(directorRepository.getOne(directorsId));
			movieRepository.save(movie);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<DirectorsDtosToMovies> getDirectorsAllMoviesWithName(String name) {
		List<DirectorsDtosToMovies> directorsDtosToMoviesList = new ArrayList<>();
		for (Directors d : directorRepository.findAll()) {
			directorsDtosToMoviesList.add(new DirectorsDtosToMovies(d));
		}
		return directorsDtosToMoviesList;
	}

	@Override
	public boolean addMovie(AddMovieRequest addMovieRequest) {
		if (addMovieRequest.getDirectorName().equals(null) && addMovieRequest.getDirectorId() != null
				&& directorRepository.existsById(addMovieRequest.getDirectorId())) {
			Directors director = directorRepository.findById(addMovieRequest.getDirectorId()).get();
			addMovieRequest.setDirectorName(director.getName());
		}
		if (!movieRepository.existsByNameAndDirectorsName(addMovieRequest.getName(),
				addMovieRequest.getDirectorName())) {
			Movies movie = new Movies(addMovieRequest);
			if (addMovieRequest.getImdbId() == null) {
				movie.setImdbRating(addMovieRequest.getImdbRating());
			} 
			else {
				movie.setImdbRating(OmdbConnection.getImdbRatingFromImdb(addMovieRequest.getImdbId()));
			}
			addMovieDirectorsByName(addMovieRequest.getDirectorName(), movie);
			movieRepository.save(movie);
			return true;
		} else {
			return false;
		}
	}
}
