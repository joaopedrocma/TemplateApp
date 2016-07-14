package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.Recusados;

public interface RecusadosRepository extends JpaRepository<Recusados, Long> {

}
