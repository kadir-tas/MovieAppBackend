package com.obss.spring.MovieApp.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

import com.obss.spring.MovieApp.model.dto.MoviesDtos;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Directors implements Serializable {


	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long directorId;
	
	@Column
    @NotEmpty(message = "*Please provide name")
	private String name;

	@Column
	private Date birthdate;

	@Column
    @NotEmpty
	private String birthplace;
	
	@ManyToMany(mappedBy = "directors")
    private List<Movies> movies;

	public Directors(String name, String birthplace/*, Date birthdate*/) {
		this.name = name;
		this.birthplace = birthplace;
	//	this.birthdate = birthdate;
	}

	public List<MoviesDtos> getMoviesDtos() {
		if(movies == null) {
			movies = new ArrayList<>();
		}
		List<MoviesDtos> moviesDtos = new ArrayList<>();
		for(Movies m : movies) {
			moviesDtos.add(new MoviesDtos(m));
		}
		return moviesDtos;
	}

}


