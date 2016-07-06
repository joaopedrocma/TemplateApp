package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.ContratoCredito;

public interface ContratoCreditoRepository extends JpaRepository<ContratoCredito, Long> {

}