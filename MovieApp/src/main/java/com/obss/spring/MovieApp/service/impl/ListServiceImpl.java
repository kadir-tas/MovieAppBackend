package com.obss.spring.MovieApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.spring.MovieApp.model.Lists;
import com.obss.spring.MovieApp.model.pk.ListsKey;
import com.obss.spring.MovieApp.model.value.ListType;
import com.obss.spring.MovieApp.repository.ListRepository;
import com.obss.spring.MovieApp.repository.MovieRepository;
import com.obss.spring.MovieApp.repository.UserRepository;
import com.obss.spring.MovieApp.service.ListService;

@Service
public class ListServiceImpl implements ListService {

	@Autowired
	ListRepository listRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MovieRepository movieRepository;

	@Override
	public boolean addWatchedList(Long movieId, String name) {
		ListsKey listsPk = new ListsKey(name, movieId, ListType.WATCHED.getListType());
		if (!listRepository.existsById(listsPk) && userRepository.findByUsername(name) != null
				&& movieRepository.existsById(movieId)) {
			Lists lists = new Lists(listsPk);
			listRepository.save(lists);
			return true;
		}
		return false;
	}

	@Override
	public boolean addFavoriteList(Long movieId, String name) {
		ListsKey listsPk = new ListsKey(name, movieId, ListType.FAVORITE.getListType());
		if (!listRepository.existsById(listsPk) && userRepository.findByUsername(name) != null
				&& movieRepository.existsById(movieId)) {
			Lists lists = new Lists(listsPk);
			listRepository.save(lists);
			return true;
		}
		return false;
	}

	@Override
	public List<Lists> getLists(String name, String type) {
		return listRepository.findByListsKeyNameAndListsKeyType(name, type);
	}

}
