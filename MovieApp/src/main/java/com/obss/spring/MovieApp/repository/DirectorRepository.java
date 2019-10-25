package com.obss.spring.MovieApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obss.spring.MovieApp.model.Directors;

@Repository
public interface DirectorRepository extends JpaRepository<Directors, Long> {
	
	public List<Directors> findDirectorsByName(String name);

	public Directors findByName(String name);

	public boolean existsByName(String name);

}
