package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.beans.Autor;

public interface AutorRepository  extends JpaRepository <Autor, Long> { }
