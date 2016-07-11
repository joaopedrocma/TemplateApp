package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

}
