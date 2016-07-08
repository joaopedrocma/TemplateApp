package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
