package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.beans.Livro;

public interface LivroRepository  extends JpaRepository <Livro, Long> { }
