package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.LimiteCredito;
import br.com.keepcred.repositories.LimiteCreditoRepository;

@Service
public class LimiteCreditoService {

	@Autowired
	private LimiteCreditoRepository limiteCreditoRepository;
	
	public List<LimiteCredito> findAll() {
		return limiteCreditoRepository.findAll();
	}

	public LimiteCredito findById(Long id) {
		return limiteCreditoRepository.findOne(id);
	}

}
