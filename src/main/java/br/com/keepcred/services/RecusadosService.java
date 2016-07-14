package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.Recusados;
import br.com.keepcred.repositories.RecusadosRepository;

@Service
public class RecusadosService {

	@Autowired
	private RecusadosRepository recusadoRepository;

	public List<Recusados> findAll() {
		return recusadoRepository.findAll();
	}

	public Recusados findById(Long id) {
		return recusadoRepository.findOne(id);
	}

	public Recusados create(Recusados recusado) {
		return recusadoRepository.save(recusado);
	}
	
	public Recusados update(Recusados recusado) {
		return recusadoRepository.save(recusado);
	}

	public void delete(Long id) {
		recusadoRepository.delete(id);
	}

}
