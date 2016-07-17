package br.com.Template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Template.entities.UserRoles;
import br.com.Template.repositories.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository userRolesRepository;

	public List<UserRoles> findAll() {
		return userRolesRepository.findAll();
	}

	public UserRoles findById(Long id) {
		return userRolesRepository.findOne(id);
	}

	public UserRoles create(UserRoles userRoles) {
		return userRolesRepository.save(userRoles);
	}

	public UserRoles update(UserRoles userRoles) {
		return userRolesRepository.save(userRoles);
	}

	public void delete(Long id) {
		userRolesRepository.delete(id);
	}

}
