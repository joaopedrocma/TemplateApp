package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.ParticipanteContaCor;
import br.com.keepcred.repositories.ParticipanteContaCorRepository;

@Service
public class ParticipanteContaCorService {

	@Autowired
	private ParticipanteContaCorRepository participanteContaCorRepository;
	
	public List<ParticipanteContaCor> findAll() {
		return participanteContaCorRepository.findAll();
	}

	public ParticipanteContaCor findById(Long id) {
		return participanteContaCorRepository.findOne(id);
	}	
}
