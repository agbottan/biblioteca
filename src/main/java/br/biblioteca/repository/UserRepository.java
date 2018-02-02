package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.beans.User;

public interface UserRepository  extends JpaRepository <User, Long> {

	public User findByUsername(String username);
}
