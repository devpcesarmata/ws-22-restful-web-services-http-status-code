/**
 * 
 */
package com.cesarmata.ws22restfulwebserviceshttpstatuscode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cesarmata.ws22restfulwebserviceshttpstatuscode.dto.User;
import com.cesarmata.ws22restfulwebserviceshttpstatuscode.service.UserService;

/**
 * @author Cesar Mata 
 *
 */
@RestController
public class HelloWorldController {
	
	@Autowired
	private UserService service;
	
	// GET /users
	// retrieveAllUsers.
	@GetMapping(path= "/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	// GET /users/{id}
	// retrieveUser (int id);
	@GetMapping(path="/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return service.findOne(id);
	}
	
	// POST /users/{id}{name}{birthday}
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		
		// CREATED -> status to return
		// /user/{id}   savedUser.getId();
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}