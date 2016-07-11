package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.ClienteInstunidade;
import br.com.keepcred.repositories.ClienteInstunidadeRepository;

@Service
public class ClienteInstunidadeService {

	@Autowired
	private ClienteInstunidadeRepository clienteInstunidadeRepository;

	public List<ClienteInstunidade> findAll() {
		return clienteInstunidadeRepository.findAll();
	}

	public ClienteInstunidade findById(Long id) {
		return clienteInstunidadeRepository.findOne(id);
	}

	public void delete(Long id) {
		clienteInstunidadeRepository.delete(id);
	}

}
