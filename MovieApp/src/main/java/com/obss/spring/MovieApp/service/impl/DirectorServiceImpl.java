package com.obss.spring.MovieApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.spring.MovieApp.model.Directors;
import com.obss.spring.MovieApp.model.dto.DirectorsDtosToMovies;
import com.obss.spring.MovieApp.repository.DirectorRepository;
import com.obss.spring.MovieApp.service.DirectorService;

@Service
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	DirectorRepository directorRepository;

	@Override
	public boolean addDirector(Directors director) {
		if (directorRepository.findByName(director.getName()) == null) {
			directorRepository.save(director);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean deleteDirector(Long directorId) {
		if (directorRepository.existsById(directorId)) {
			directorRepository.delete(directorRepository.getOne(directorId));
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updateDirector(Directors director) {
		if (directorRepository.existsById(director.getDirectorId())) {
			directorRepository.save(director);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public DirectorsDtosToMovies searchDirector(String name) {
		if(directorRepository.existsByName(name))
			return new DirectorsDtosToMovies(directorRepository.findByName(name));
		else {
			return null;
		}
	}


	@Override
	public List<DirectorsDtosToMovies> searchAllDirectorsWithName(String name) {
		List<DirectorsDtosToMovies> list = new ArrayList<>();
		for(Directors d : directorRepository.findDirectorsByName(name)) {
			list.add(new DirectorsDtosToMovies(d));
		}
		return list;
	}


	@Override
	public List<DirectorsDtosToMovies> getAllDirectors() {
		List<DirectorsDtosToMovies> directorsDtosToMoviesList = new ArrayList<>();
		for (Directors d : directorRepository.findAll()) {
			directorsDtosToMoviesList.add(new DirectorsDtosToMovies(d));
		}
		return directorsDtosToMoviesList;
	}

}
