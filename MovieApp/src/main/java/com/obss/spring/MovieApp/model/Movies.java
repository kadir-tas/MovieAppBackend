package com.obss.spring.MovieApp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.obss.spring.MovieApp.controller.request.AddMovieFromImdb;
import com.obss.spring.MovieApp.controller.request.AddMovieRequest;
import com.obss.spring.MovieApp.model.dto.DirectorsDtos;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movies implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long movieId;
	
	@Column
    @NotEmpty(message = "*Please provide movie name")
	private String name;

	@Column
	@NotNull(message = "*Please provide release date")
	private int releasedate;

	@Column
	private double imdbRating;

	@Column
	private int duration;
	
	@Column
    @NotEmpty
	private String genre;	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.ALL })
    @JoinTable(
        name = "director_movies", 
        joinColumns = { @JoinColumn(name = "movieId") }, 
        inverseJoinColumns = { @JoinColumn(name = "directorId") })
	private List<Directors> directors;

	public List<DirectorsDtos> getDirectorsDtos(){
		List<DirectorsDtos> directorsDtos = new ArrayList<>();
		for(Directors a : directors) {
			directorsDtos.add(new DirectorsDtos(a));
		}
		return directorsDtos;
	}

	public Movies(AddMovieFromImdb addMovieFromImdb) {
		this.name = addMovieFromImdb.getName();
		this.releasedate = addMovieFromImdb.getReleasedate();
		this.duration = addMovieFromImdb.getDuration();
		this.genre = addMovieFromImdb.getGenre();
		this.imdbRating = addMovieFromImdb.getImdbRating();
	}
	
	public Movies(AddMovieRequest addMovieRequest) {
		this.name = addMovieRequest.getName();
		this.releasedate = addMovieRequest.getReleasedate();
		this.duration = addMovieRequest.getDuration();
		this.genre = addMovieRequest.getGenre();
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<Directors> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Directors> directors) {
		this.directors = directors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
