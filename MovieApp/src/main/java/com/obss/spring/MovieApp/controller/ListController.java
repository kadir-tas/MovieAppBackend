package com.obss.spring.MovieApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.obss.spring.MovieApp.controller.request.AddListRequest;
import com.obss.spring.MovieApp.model.Lists;
import com.obss.spring.MovieApp.model.Message;
import com.obss.spring.MovieApp.service.ListService;

@RestController
@RequestMapping("/list")
public class ListController {

	@Autowired
	ListService listService;
	
	@PutMapping("/favorite")
	public ResponseEntity<?> addFavorite(@RequestBody AddListRequest addListRequest) {
		if(listService.addFavoriteList(addListRequest.getMovieId(), addListRequest.getName())) {
			return new ResponseEntity<>(new Message("Saved"), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message("Something went wrong"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/watched")
	public ResponseEntity<?> addWatched(@RequestBody AddListRequest addListRequest) {
		if(listService.addWatchedList(addListRequest.getMovieId(), addListRequest.getName())) {
			return new ResponseEntity<>(new Message("Saved"), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message("Something went wrong"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<?> getFavorites(@RequestParam String type, String name) {
		List<Lists> lists = listService.getLists(name, type);
		if(lists != null) {
			return new ResponseEntity<>(lists, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new Message("Something went wrong"), HttpStatus.BAD_REQUEST);
		}
	}
	
}
