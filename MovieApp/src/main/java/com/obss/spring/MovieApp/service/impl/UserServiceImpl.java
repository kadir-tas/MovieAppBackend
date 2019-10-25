package com.obss.spring.MovieApp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.obss.spring.MovieApp.controller.request.AddUserRequest;
import com.obss.spring.MovieApp.model.Authorities;
import com.obss.spring.MovieApp.model.Users;
import com.obss.spring.MovieApp.model.dto.UsersDtos;
import com.obss.spring.MovieApp.model.value.Role;
import com.obss.spring.MovieApp.repository.ListRepository;
import com.obss.spring.MovieApp.repository.UserRepository;
import com.obss.spring.MovieApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ListRepository listRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UsersDtos searchUserByName(String username) {
		Users user = userRepository.findByUsername(username);
		if (user != null) {
			return new UsersDtos(user);
		}
		return null;
	}

	@Override
	public boolean addUsers(AddUserRequest addUserRequest) {
		if (userRepository.existsByUsername(addUserRequest.getUsername())) return false;// username already taken
		if (userRepository.existsByEmail(addUserRequest.getEmail())) return false; // email already taken
		
		addUserRequest.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
		Users user = new Users(addUserRequest);
		userRepository.save(user);
		defineUserRole(addUserRequest.getUsername(), addUserRequest.getAuthority());
		return true;
	}

	@Override
	public boolean loginUser(Users user) {

		return false;
	}

	@Override
	public boolean logoutUser(Users user) {
		return false;
	}

	@Override
	public boolean deleteUser(Long id) {
		if (id == null)
			return false;
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(Users user) {
		if (userRepository.existsById(user.getUserId())) {
			userRepository.save(user);
		}
		return false;
	}

	@Override
	public UsersDtos getUserById(Long userId) {
		if (userRepository.existsById(userId)) {
			return new UsersDtos(userRepository.getOne(userId));
		}
		return null;
	}

	@Override
	public List<UsersDtos> getAllUsers() {
		List<UsersDtos> usersDto = new ArrayList<>();
		for (Users u : userRepository.findAll()) {
			usersDto.add(new UsersDtos(u));
		}
		return usersDto;
	}

	@Override
	public boolean defineUserRole(String username, String authority) {
		if (username == null || authority == null) return false;

		authority = authority.toUpperCase();
		Users user = userRepository.findByUsername(username);
		if (user != null && (authority.contains(Role.ROLE_ADMIN.getRole()) 
				|| authority.contains(Role.ROLE_USER.getRole()))) {
			
			addUserRole(user, initAuth(authority.contains(Role.ROLE_ADMIN.getRole()) 
					? Role.ROLE_ADMIN.toString() : Role.ROLE_USER.toString(), user));
			return true;
		}
		return false;
	}

	public void addUserRole(Users user, Authorities auth) {
		if (user.getAuthorities() == null) {
			Set<Authorities> list = new HashSet<>();
			list.add(auth);
			user.setAuthorities(list);
		} else {
			user.getAuthorities().add(auth);
		}
		userRepository.save(user);
	}

	public Authorities initAuth(String authority, Users user) {
		return new Authorities(authority, user);
	}

}
