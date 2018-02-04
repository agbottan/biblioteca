package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.model.Livro;

public interface LivroRepository  extends JpaRepository <Livro, Long> { }
