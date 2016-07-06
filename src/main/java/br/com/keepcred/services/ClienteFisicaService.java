package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.keepcred.entities.ClienteFisica;
import br.com.keepcred.repositories.ClienteFisicaRepository;

@Configuration
public class ClienteFisicaService {

	@Autowired
	private ClienteFisicaRepository clienteFisicaRepository;

	public List<ClienteFisica> findAll() {
		return clienteFisicaRepository.findAll();
	}

	public ClienteFisica findClienteFisicaById(Long id) {
		return clienteFisicaRepository.findOne(id);
	}

}
