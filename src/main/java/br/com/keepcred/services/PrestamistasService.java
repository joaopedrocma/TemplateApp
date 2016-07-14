package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.Prestamistas;
import br.com.keepcred.repositories.PrestamistasRepository;

@Service
public class PrestamistasService {

	@Autowired
	private PrestamistasRepository prestamistaRepository;

	public List<Prestamistas> getPrestamistasComContrato(){
		return prestamistaRepository.getPrestamistasComContrato();
	}
	
	public List<Prestamistas> getPrestamistasSemContrato(){
		return prestamistaRepository.getPrestamistasSemContrato();
	}
	
	public Prestamistas create(Prestamistas prestamista) {
		return prestamistaRepository.save(prestamista);
	}
	
	public List<Prestamistas> findAll() {
		return prestamistaRepository.findAll();
	}

	public Prestamistas findById(Long id) {
		return prestamistaRepository.findOne(id);
	}
	
}