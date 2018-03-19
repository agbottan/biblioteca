package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.model.Autor;

public interface AutorRepository  extends JpaRepository <Autor, Long> {

	public Autor findByNome(String nome);
}
