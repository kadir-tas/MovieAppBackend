package com.obss.spring.MovieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obss.spring.MovieApp.model.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long>{
	public Movies findByName(String name);
	
	public Movies findMoviesByNameAndDirectors(String name, String directors);

	public boolean existsByNameAndDirectorsName(String name, String directorsName);
}
