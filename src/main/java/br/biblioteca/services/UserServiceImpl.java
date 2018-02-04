package br.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// import br.biblioteca.model.Role;
import br.biblioteca.model.User;
import br.biblioteca.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // !!! user.getRoles().add(new Role("ROLE_BASIC"));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}