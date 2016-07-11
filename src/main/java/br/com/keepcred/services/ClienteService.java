package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.Cliente;
import br.com.keepcred.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteServiceRepository;

	public List<Cliente> findAll() {
		return clienteServiceRepository.findAll();
	}

	public Cliente findById(Long id) {
		return clienteServiceRepository.findOne(id);
	}
}
