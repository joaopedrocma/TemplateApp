package br.com.keepcred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepcred.entities.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long>{

}
