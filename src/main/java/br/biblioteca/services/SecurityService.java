package br.biblioteca.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
 
  String findLoggedInUsername();

  UserDetails findLoggedInUser();
 
  Exception login(String username, String password);
}
