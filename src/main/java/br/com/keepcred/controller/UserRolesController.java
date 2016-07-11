package br.com.keepcred.controller;

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

import br.com.keepcred.entities.UserRoles;
import br.com.keepcred.services.UserRolesService;

@Controller
@RequestMapping("/userRoles")
public class UserRolesController {

	@Autowired
	private UserRolesService userRolesService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserRoles> findAllUserRoles() {
		return userRolesService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserRoles findUserRolesById(@PathVariable("id") Long id) {
		return userRolesService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<UserRoles> createUserRoles(@RequestBody UserRoles userRoles) {
		UserRoles savedUserRoles = userRolesService.create(userRoles);
		return new ResponseEntity<UserRoles>(savedUserRoles, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<UserRoles> updateUserRoles(@RequestBody UserRoles userRoles) {
		UserRoles savedUserRoles = userRolesService.update(userRoles);
		return new ResponseEntity<UserRoles>(savedUserRoles, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteUserRoles(@PathVariable("id") Long id) {
		userRolesService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
