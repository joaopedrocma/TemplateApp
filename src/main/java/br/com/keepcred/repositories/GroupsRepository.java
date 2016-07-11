package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.Groups;

public interface GroupsRepository extends JpaRepository<Groups, Long>{

}
