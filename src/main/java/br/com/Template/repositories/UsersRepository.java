package br.com.Template.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Template.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

}
