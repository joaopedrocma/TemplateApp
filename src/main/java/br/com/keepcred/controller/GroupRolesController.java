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

import br.com.keepcred.entities.GroupRoles;
import br.com.keepcred.services.GroupRolesService;

@Controller
@RequestMapping("/groupRoles")
public class GroupRolesController {

	@Autowired
	private GroupRolesService groupRolesService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<GroupRoles> findAllGroupRoles() {
		return groupRolesService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public GroupRoles findGroupRolesById(@PathVariable("id") Long id) {
		return groupRolesService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<GroupRoles> createGroupRoles(@RequestBody GroupRoles groupRoles) {
		GroupRoles savedGroupRoles = groupRolesService.create(groupRoles);
		return new ResponseEntity<GroupRoles>(savedGroupRoles, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<GroupRoles> updateGroupRoles(@RequestBody GroupRoles groupRoles) {
		GroupRoles savedGroupRoles = groupRolesService.update(groupRoles);
		return new ResponseEntity<GroupRoles>(savedGroupRoles, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteGroupRoles(@PathVariable("id") Long id) {
		groupRolesService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
