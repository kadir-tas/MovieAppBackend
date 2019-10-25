package com.obss.spring.MovieApp.model.dto;

import java.io.Serializable;

import com.obss.spring.MovieApp.model.Directors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsDtos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	
	private Long directorsId;

	public DirectorsDtos(Directors directors) {
		this.name = directors.getName();
		this.directorsId = directors.getDirectorId();
	}

}
