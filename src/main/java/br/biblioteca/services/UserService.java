package br.biblioteca.services;

import br.biblioteca.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
