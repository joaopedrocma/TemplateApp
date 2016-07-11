package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.Recusado;
import br.com.keepcred.repositories.RecusadoRepository;

@Service
public class RecusadoService {

	@Autowired
	private RecusadoRepository recusadoRepository;

	public List<Recusado> findAll() {
		return recusadoRepository.findAll();
	}

	public Recusado findById(Long id) {
		return recusadoRepository.findOne(id);
	}

	public Recusado create(Recusado recusado) {
		return recusadoRepository.save(recusado);
	}
	
	public Recusado update(Recusado recusado) {
		return recusadoRepository.save(recusado);
	}

	public void delete(Long id) {
		recusadoRepository.delete(id);
	}

}
