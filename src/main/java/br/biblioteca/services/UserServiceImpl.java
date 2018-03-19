package br.biblioteca.services;

import br.biblioteca.model.User;
import br.biblioteca.model.Role;
import br.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        // HashSet<Role> roles = new HashSet<>(roleRepository.findAll());

        HashSet<Role> roles = new HashSet<>();

        // ROLE_BASIC
        Role roleBasic = new Role();
        roleBasic.setId(1L);
        roleBasic.setName("ROLE_BASIC");
        roles.add(roleBasic);

        // ROLE_ADMIN
        /**/
        Role roleAdmin = new Role();
        roleAdmin.setId(2L);
        roleAdmin.setName("ROLE_ADMIN");
        roles.add(roleAdmin);
        /**/

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
