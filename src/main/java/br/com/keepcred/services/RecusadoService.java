package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.keepcred.entities.Recusado;
import br.com.keepcred.repositories.RecusadoRepository;

@Configuration
public class RecusadoService {

	@Autowired
	private RecusadoRepository recusadoRepository;

	public List<Recusado> findAll() {
		return recusadoRepository.findAll();
	}

	public Recusado findRecusadoById(Long id) {
		return recusadoRepository.findOne(id);
	}

	public Recusado create(Recusado recusado) {
		return recusadoRepository.save(recusado);
	}
	
	public Recusado update(Recusado recusado) {
		return recusadoRepository.save(recusado);
	}

	public void deleteRecusado(Long id) {
		recusadoRepository.delete(id);
	}

}
