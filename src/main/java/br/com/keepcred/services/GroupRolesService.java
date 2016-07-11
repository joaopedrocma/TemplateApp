package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.GroupRoles;
import br.com.keepcred.repositories.GroupRolesRepository;

@Service
public class GroupRolesService {

	@Autowired
	private GroupRolesRepository groupRolesRepository;

	public List<GroupRoles> findAll() {
		return groupRolesRepository.findAll();
	}

	public GroupRoles findById(Long id) {
		return groupRolesRepository.findOne(id);
	}

	public GroupRoles create(GroupRoles groupRoles) {
		return groupRolesRepository.save(groupRoles);
	}

	public GroupRoles update(GroupRoles groupRoles) {
		return groupRolesRepository.save(groupRoles);
	}

	public void delete(Long id) {
		groupRolesRepository.delete(id);
	}

}
