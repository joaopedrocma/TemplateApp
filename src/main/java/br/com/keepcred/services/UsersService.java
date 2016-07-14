package br.com.keepcred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.keepcred.entities.Users;
import br.com.keepcred.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Users> findAll() {
		return userRepository.findAll();
	}

	public Users findById(Long id) {
		return userRepository.findOne(id);
	}

	public Users create(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public Users update(Users user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}

}
