package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.keepcred.entities.ClienteInstunidade;
import br.com.keepcred.repositories.ClienteInstunidadeRepository;

@Configuration
public class ClienteInstunidadeService {

	@Autowired
	private ClienteInstunidadeRepository clienteInstunidadeRepository;

	public List<ClienteInstunidade> findAll() {
		return clienteInstunidadeRepository.findAll();
	}

	public ClienteInstunidade findClienteInstunidadeById(Long id) {
		return clienteInstunidadeRepository.findOne(id);
	}

	public void deleteClienteInstunidade(Long id) {
		clienteInstunidadeRepository.delete(id);
	}

}
