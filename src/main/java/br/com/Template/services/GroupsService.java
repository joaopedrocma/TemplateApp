package br.com.Template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Template.entities.Groups;
import br.com.Template.repositories.GroupsRepository;

@Service
public class GroupsService {

	@Autowired
	private GroupsRepository groupsRepository;

	public List<Groups> findAll() {
		return groupsRepository.findAll();
	}

	public Groups findById(Long id) {
		return groupsRepository.findOne(id);
	}

	public Groups create(Groups groups) {
		return groupsRepository.save(groups);
	}

	public Groups update(Groups groups) {
		return groupsRepository.save(groups);
	}

	public void delete(Long id) {
		groupsRepository.delete(id);
	}

}
