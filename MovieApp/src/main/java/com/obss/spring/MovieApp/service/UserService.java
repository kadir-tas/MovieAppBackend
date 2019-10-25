package com.obss.spring.MovieApp.service;

import java.util.List;

import com.obss.spring.MovieApp.controller.request.AddUserRequest;
import com.obss.spring.MovieApp.model.Users;
import com.obss.spring.MovieApp.model.dto.UsersDtos;

public interface UserService{
	
	public UsersDtos searchUserByName(String userName);
	
	public boolean addUsers(AddUserRequest addUserRequest);
	
	public boolean loginUser(Users user);
	
	public boolean logoutUser(Users user);
	
	public boolean deleteUser(Long id);
	
	public boolean updateUser(Users user);

	public UsersDtos getUserById(Long userId);

	public List<UsersDtos> getAllUsers();
	
	boolean defineUserRole(String username, String authority);
}
