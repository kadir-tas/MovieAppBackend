package com.obss.spring.MovieApp.service;

import java.util.List;

import com.obss.spring.MovieApp.model.Lists;

public interface ListService {

	public boolean addWatchedList(Long movieId, String name);
	
	public boolean addFavoriteList(Long movieId, String name);
	
	public List<Lists> getLists(String name, String type);
	
}
