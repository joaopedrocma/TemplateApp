package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.keepcred.entities.Prestamista;
import br.com.keepcred.repositories.PrestamistaRepository;

@Configuration
public class PrestamistaService {

	@Autowired
	private PrestamistaRepository prestamistaServiceRepository;

	public List<Prestamista> findAll() {
		return prestamistaServiceRepository.findAll();
	}

	public Prestamista findPrestamistaById(Long id) {
		return prestamistaServiceRepository.findOne(id);
	}
	
}