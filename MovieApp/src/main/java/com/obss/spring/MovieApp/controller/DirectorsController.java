package com.obss.spring.MovieApp.controller;

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

import com.obss.spring.MovieApp.model.Directors;
import com.obss.spring.MovieApp.model.Message;
import com.obss.spring.MovieApp.model.dto.DirectorsDtosToMovies;
import com.obss.spring.MovieApp.service.DirectorService;

@RestController
@RequestMapping("/director")
public class DirectorsController {
	
	@Autowired
	DirectorService directorService;
	
	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<?> addDirector(@RequestBody Directors director) {
		if(directorService.addDirector(director)) {
			return new ResponseEntity<>(new Message("Saved"), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message("There is already a director with id : " + director.getDirectorId()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDirector(@PathVariable Long id){
		if(directorService.deleteDirector(id)) {
			return new ResponseEntity<>(new Message("Deleted"), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message("There isn't a director with id : " + id), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> search(@RequestParam String name) {
		DirectorsDtosToMovies directorsDtosToMovies = directorService.searchDirector(name);
		if(directorsDtosToMovies == null) {
			return new ResponseEntity<>(new Message("There isn't a director with name : " + name), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(directorsDtosToMovies, HttpStatus.OK);
		}
	}
	
	@GetMapping("/list/{name}")
	public ResponseEntity<?> searchList(@PathVariable String name) {
		List<DirectorsDtosToMovies> directorsDtosToMoviesList = directorService.searchAllDirectorsWithName(name);
		if(directorsDtosToMoviesList == null) {
			return new ResponseEntity<>(new Message("There isn't a director with name : " + name), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(directorsDtosToMoviesList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllDirectors() {
		List<DirectorsDtosToMovies> directorsDtosToMoviesList = directorService.getAllDirectors();
		if(directorsDtosToMoviesList == null || directorsDtosToMoviesList.isEmpty()) {
			return new ResponseEntity<>(new Message("No any director to show"), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(directorsDtosToMoviesList, HttpStatus.OK);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> update(@RequestBody Directors director) {
		if(directorService.updateDirector(director)) {
			return new ResponseEntity<>(new DirectorsDtosToMovies(director), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message("Something went wrong!"), HttpStatus.BAD_REQUEST);
		}
	}
}