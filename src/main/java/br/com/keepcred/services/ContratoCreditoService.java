package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.ContratoCredito;
import br.com.keepcred.repositories.ContratoCreditoRepository;

@Service
public class ContratoCreditoService {

	@Autowired
	private ContratoCreditoRepository contratoCreditoRepository;

	public List<ContratoCredito> findAll() {
		return contratoCreditoRepository.findAll();
	}

	public ContratoCredito findById(Long id) {
		return contratoCreditoRepository.findOne(id);
	}

}
