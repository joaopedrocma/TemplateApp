package br.com.Template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.Template.entities.Users;
import br.com.Template.services.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService userService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Users> findAllUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Users findUserById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		Users savedUser = userService.create(user);
		return new ResponseEntity<Users>(savedUser, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Users> updateUser(@RequestBody Users user) {
		Users savedUser = userService.update(user);
		return new ResponseEntity<Users>(savedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
