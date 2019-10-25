package com.obss.spring.MovieApp.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.obss.spring.MovieApp.controller.request.AddDirectorToMovieRequest;
import com.obss.spring.MovieApp.controller.request.AddMovieRequest;
import com.obss.spring.MovieApp.controller.request.SearchImdbRequest;
import com.obss.spring.MovieApp.model.Message;
import com.obss.spring.MovieApp.model.Movies;
import com.obss.spring.MovieApp.model.dto.DirectorsDtosToMovies;
import com.obss.spring.MovieApp.model.dto.MoviesDtosToDirectors;
import com.obss.spring.MovieApp.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	public MovieService movieService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/addimdb")
	public ResponseEntity<?> addMovieName(@RequestBody SearchImdbRequest searchImdbRequest) throws MalformedURLException  {
		Object obj = movieService.addMovieWithName(searchImdbRequest.getMovieName());
		if (obj instanceof Message) {
			return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(obj, HttpStatus.OK);
		}
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/directors")
	public ResponseEntity<?> addDirectorsToMovie(@RequestBody AddDirectorToMovieRequest addDirectorToMovieRequest) {
		if (!movieService.addMovieDirectorsById(addDirectorToMovieRequest.getDirectorId(),
				addDirectorToMovieRequest.getMovieId())) {
			return new ResponseEntity<>(new Message("Something went wrong!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Added"), HttpStatus.OK);
		}
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		if (!movieService.deleteMovie(id)) {
			return new ResponseEntity<>(new Message("There isn't a movie to delete!"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new Message("Deleted!"), HttpStatus.OK);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/add")
	public ResponseEntity<?> addMovie(@RequestBody  AddMovieRequest addMovieRequest)  {
		
		if (movieService.addMovie(addMovieRequest)) {
			return new ResponseEntity<>(new Message("Saved"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Movie already exist"), HttpStatus.BAD_REQUEST);
		}
	}

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> updateMovie(@RequestBody Movies movie) {

		if (!movieService.updateMovie(movie)) {
			return new ResponseEntity<>(new Message("No any movie to update"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new MoviesDtosToDirectors(movie), HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> searchMovie(@PathVariable Long id) {

		MoviesDtosToDirectors moviesDtosToDirectors = movieService.getMovieById(id);
		if (moviesDtosToDirectors == null) {
			return new ResponseEntity<>(new Message("No any movie to show"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(moviesDtosToDirectors, HttpStatus.OK);
		}
	}

	@GetMapping
	public ResponseEntity<?> searchMovieByName(@RequestParam String name) {

		MoviesDtosToDirectors moviesDtosToDirectors = movieService.getMovieByName(name);
		if (moviesDtosToDirectors == null) {
			return new ResponseEntity<>(new Message("No any movie to show"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(moviesDtosToDirectors, HttpStatus.OK);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllList() {

		List<MoviesDtosToDirectors> moviesDtosToDirectorsList = movieService.getAllMovies();
		if (moviesDtosToDirectorsList == null || moviesDtosToDirectorsList.isEmpty()) {
			return new ResponseEntity<>(new Message("No any movie to show"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(moviesDtosToDirectorsList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/director")
	public ResponseEntity<?> getDirectorsAllMovies(@RequestParam String name) {
		List<DirectorsDtosToMovies> directorsDtosToMovies = movieService.getDirectorsAllMoviesWithName(name);
		if(directorsDtosToMovies == null) {
			return new ResponseEntity<>(new Message("There isn't a director with name : " + name), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(directorsDtosToMovies, HttpStatus.OK);
		}
	}

}
