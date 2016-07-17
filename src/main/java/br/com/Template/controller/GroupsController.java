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

import br.com.Template.entities.Groups;
import br.com.Template.services.GroupsService;

@Controller
@RequestMapping("/groups")
public class GroupsController {

	@Autowired
	private GroupsService groupsService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Groups> findAllGroups() {
		return groupsService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Groups findGroupsById(@PathVariable("id") Long id) {
		return groupsService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Groups> createGroups(@RequestBody Groups groups) {
		Groups savedGroups = groupsService.create(groups);
		return new ResponseEntity<Groups>(savedGroups, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Groups> updateGroups(@RequestBody Groups groups) {
		Groups savedGroups = groupsService.update(groups);
		return new ResponseEntity<Groups>(savedGroups, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteGroups(@PathVariable("id") Long id) {
		groupsService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
