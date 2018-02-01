package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.beans.Usuario;

public interface UsuarioRepository  extends JpaRepository <Usuario, Long> { }
