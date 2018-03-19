package br.biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.model.Emprestimo;

public interface EmprestimoRepository  extends JpaRepository <Emprestimo, Long> {

	public Emprestimo findByDataEmprestimo(String dataEmprestimo);
	public Emprestimo findByDataDevolucao(String dataDevolucao);
}
