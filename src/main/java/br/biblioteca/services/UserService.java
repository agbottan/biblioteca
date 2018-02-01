package br.biblioteca.services;

import br.biblioteca.beans.User;

import java.util.List;

public interface UserService {
	
	void save(User user);

	User findByUsername(String username);

	List<User> findAll();
	
}