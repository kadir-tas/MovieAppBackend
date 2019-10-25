package com.obss.spring.MovieApp.service;

import java.util.List;

import com.obss.spring.MovieApp.model.Directors;
import com.obss.spring.MovieApp.model.dto.DirectorsDtosToMovies;

public interface DirectorService {
	public boolean addDirector(Directors director);

	public boolean deleteDirector(Long directorId);

	public boolean updateDirector(Directors director);

	public DirectorsDtosToMovies searchDirector(String name);
	
	public List<DirectorsDtosToMovies> searchAllDirectorsWithName(String name);

	public List<DirectorsDtosToMovies> getAllDirectors();
}
