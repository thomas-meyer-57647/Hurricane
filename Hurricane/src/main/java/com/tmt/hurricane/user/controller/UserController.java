package com.tmt.hurricane.user.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.user.model.User;

/**
 * this is the rest controller for the user management
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	private static List<User> users = new ArrayList<User>();
	
	static {
		User user;

		user = new User(
				"Sean",
				"Connery",
				"sean.connery@email.com",
				"abcd"
		);
		user.setId(1);
		users.add(user);


		user = new User(
				"Roger",
				"Moore",
				"roger.moore@email.com",
				"fdeg"
		);
		user.setId(2);
		users.add(user);

		
		user = new User(
				"George",
				"Lazenby",
				"george.Lazenby@email.com",
				"1234"
		);
		user.setId(3);
		users.add(user);

		
		user = new User(
				"Timothy",
				"Dalton",
				"timothy.dalton@email.com",
				"5678"
		);
		user.setId(4);
		users.add(user);
		
		user = new User(
				"Pierce",
				"Brosnan",
				"pierce.brosnan@email.com",
				"5678"
		);
		user.setId(5);
		users.add(user);

		user = new User(
				"Daniel",
				"Craig",
				"daniel.craig@email.com",
				"5678"
		);
		user.setId(6);
		users.add(user);		
	}
	
	/**
	 * get the list of users
	 * 
	 * @return String
	 * @throws ResourceNotFoundException 
	 */
	@GetMapping(path="/api/v1/user/list")
	public List<User> getList() throws ResourceNotFoundException {
		return users;
	}
	
	/**
	 * get a user
	 * 
	 * @return JSON
	 */
	@GetMapping(path="/api/v1/user/{id}")
	public User getUser(@PathVariable long id) {
		return findById(id); 
	}

	/**
	 * update a user
	 * 
	 */
	
	/**
	 * delete a user
	 * 
	 * @return JSON
	 */
	@DeleteMapping(path="/api/v1/user/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		User user = deleteById(id);
		
		if ( user != null ) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public User deleteById(long id) {
		User user = findById(id);
		
		if (user == null) 
			return null;
		
		if (users.remove(user))
			return user;
		
		return null;
	}
	
	public User findById(long id) {
		for(User user:users) {
			if ( user.getId() == id ) 
				return user;
		}
		
		return null;
	}
	
}
