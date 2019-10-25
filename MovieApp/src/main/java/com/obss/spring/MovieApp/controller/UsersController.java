package com.obss.spring.MovieApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obss.spring.MovieApp.controller.request.AddUserRequest;
import com.obss.spring.MovieApp.controller.request.UserRoleRequest;
import com.obss.spring.MovieApp.model.Message;
import com.obss.spring.MovieApp.model.Users;
import com.obss.spring.MovieApp.model.dto.UsersDtos;
import com.obss.spring.MovieApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> updateUser(@RequestBody Users user) {

		if (userService.updateUser(user)) {
			return new ResponseEntity<>(new UsersDtos(user), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new Message("No any user to update"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<?> getAllUser() {

		List<UsersDtos> usersList = userService.getAllUsers();
		if (usersList == null || usersList.isEmpty()) {
			return new ResponseEntity<>(new Message("No any user to show"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(usersList, HttpStatus.OK);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public ResponseEntity<?> searchUser(@PathVariable Long id) {

		UsersDtos usersDto = userService.getUserById(id);
		if (usersDto == null) {
			return new ResponseEntity<>(new Message("No any user to show"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(usersDto, HttpStatus.OK);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		if (userService.deleteUser(id)) {
			return new ResponseEntity<>(new Message("Deleted!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("There isn't a user to delete!"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<?> addUser(@RequestBody AddUserRequest addUserRequest) {
		if (userService.addUsers(addUserRequest)){
			return new ResponseEntity<>(new Message("Added"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Something went wrong!"), HttpStatus.OK);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/role")
	public ResponseEntity<?> addUserRole(@RequestBody UserRoleRequest userRoleRequest) {
		if (userService.defineUserRole(userRoleRequest.getUsername(), userRoleRequest.getAuthority())){
			return new ResponseEntity<>(new Message("Added"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Something went wrong!"), HttpStatus.OK);
		}
	}

}