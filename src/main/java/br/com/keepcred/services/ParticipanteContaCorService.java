package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.keepcred.entities.ParticipanteContaCor;
import br.com.keepcred.repositories.ParticipanteContaCorRepository;

@Configuration
public class ParticipanteContaCorService {

	@Autowired
	private ParticipanteContaCorRepository participanteContaCorRepository;
	
	public List<ParticipanteContaCor> findAll() {
		return participanteContaCorRepository.findAll();
	}

	public ParticipanteContaCor findParticipanteContaCorById(Long id) {
		return participanteContaCorRepository.findOne(id);
	}	
}
