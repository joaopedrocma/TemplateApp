package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.LimiteCredito;

public interface LimiteCreditoRepository extends JpaRepository<LimiteCredito, Long> {

}
