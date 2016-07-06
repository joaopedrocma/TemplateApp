package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.ClienteInstunidade;

public interface ClienteInstunidadeRepository extends JpaRepository<ClienteInstunidade, Long> {
	
}
