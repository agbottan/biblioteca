package br.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import br.biblioteca.beans.Role;
import br.biblioteca.beans.User;

@Repository
public class UserRepository {
	
	List<User> users = new ArrayList<>();

	UserRepository() {
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		/* !!!
        User basic = new User("teste", passwordEncoder.encode("123456"));
        basic.getRoles().add(new Role("ROLE_BASIC"));
        users.add(basic);
        
        User admin = new User("admin", passwordEncoder.encode("123456"));
        admin.getRoles().add(new Role("ROLE_BASIC"));
        admin.getRoles().add(new Role("ROLE_ADMIN"));
        users.add(admin);
        */
	}

	public User findByUsername(String username) {

		User user = null;

		for (User u : users) {

			if (u.getUsername().equals(username)) {
				user = u;
			}

		}

		return user;
	}

	public void save(User user) {
		users.add(user);
	}

	public List<User> findAll(){
		return users;
	}
}
