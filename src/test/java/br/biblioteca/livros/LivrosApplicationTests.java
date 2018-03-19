package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.repository.AutorRepository;
import br.biblioteca.repository.LivroRepository;
import br.biblioteca.repository.EmprestimoRepository;
import br.biblioteca.model.Autor;
import br.biblioteca.model.Livro;
import br.biblioteca.model.Emprestimo;

/*
------------------
	LEMBRETES
------------------

	CRUD -> 'Create' | 'Read' | 'Delete' | 'Update'
*/


@RunWith(SpringRunner.class)
@SpringBootTest
public class LivrosApplicationTests {

	/*
	--------------------------------
		TESTE 'CRUD' DE AUTORES
	--------------------------------
	*/

	@Autowired
	AutorRepository autorRepository;

	/*
		TESTA A BUSCA DE UM AUTOR PELO NOME
	*/
	@Test
	public void testeBuscarAutorPorNome() {

		/*
		Busca o autor com nome 'Fulado de Tal', que não consta no banco inicial, e verifica o resultado, que deve ser nulo
		*/
		Autor autorNaoEncontrado = this.autorRepository.findByNome("Fulano de Tal");
		assertThat(autorNaoEncontrado).isNull();
		
		/*
		Busca o autor 'Machado de Assis', que consta no banco inicial, e verifica o resultado, que deve ser 'não nulo', e a propriedade nome, que deve ser 'Machado de Assis'
		*/
		Autor autorEncontrado = this.autorRepository.findByNome("Machado de Assis");
		assertThat(autorEncontrado).isNotNull();
		assertThat(autorEncontrado.getNome()).isEqualTo("Machado de Assis");
	}

	/*
		TESTA A ALTERAÇÃO DO NOME DE UM AUTOR
	*/
	@Test
	public void testeAlterarNomeAutor() {

		// Busca o autor com nome 'Machado de Assis', que consta no banco inicial, altera o nome para 'Machado de Assis <ALTERADO>' e armazena a ID do autorTeste para comparação posterior
		Autor autorTeste = this.autorRepository.findByNome("Machado de Assis");
		Long autorTesteId = autorTeste.getId();

		// Altera o nome do autorTeste 'Machado de Assis' para 'Machado de Assis <ALTERADO>' e salva no banco
		autorTeste.setNome("Machado de Assis <ALTERADO>");
		this.autorRepository.save(autorTeste);

		// Busca o autor com a Id de 'autorTeste', que deve se referir ao mesmo registro no banco
		Autor autorTesteConfirma = this.autorRepository.findOne(autorTesteId);

		// Verifica se os nomes de 'autorTeste' e 'autorTesteAlterado' são iguais
		assertThat(autorTesteConfirma.getNome()).isEqualTo(autorTeste.getNome());

		// Restaura o nome original para não alterar o banco
		autorTeste.setNome("Machado de Assis");
		this.autorRepository.save(autorTeste);
	}

	/*
		TESTA A INCLUSÃO DE UM NOVO AUTOR
	*/
	@Test
	public void testeIncluirAutor() {

		// Cria um novo autor com nome 'TESTE DE INCLUSÃO' e insere no banco
		Autor autorInserido = new Autor();
		autorInserido.setNome("TESTE DE INCLUSÃO");
		this.autorRepository.save(autorInserido);

		// Verifica se há no banco um autor com o nome 'TESTE DE INCLUSÃO', que é o que acabamos de inserir
		Autor autorEncontrado = this.autorRepository.findByNome("TESTE DE INCLUSÃO");
		assertThat(autorEncontrado).isNotNull();

		// Remove o autor incluido no teste, para que o banco retorne ao estado inicial
		this.autorRepository.delete(autorEncontrado);
	}

	/*
		TESTA A EXCLUSÃO DE UM AUTOR
	*/
	@Test
	public void testeExcluirAutor() {

		// Cria um novo autor com nome 'TESTE DE EXCLUSÃO' e insere no banco
		Autor autorExcluido = new Autor();
		autorExcluido.setNome("TESTE DE EXCLUSÃO");
		this.autorRepository.save(autorExcluido);

		// Verifica se há no banco um autor com o nome 'TESTE DE EXCLUSÃO', que é o que acabamos de inserir
		autorExcluido = this.autorRepository.findByNome("TESTE DE EXCLUSÃO");
		assertThat(autorExcluido).isNotNull();

		// Remove o autor incluido no teste e faz uma busca de autor pelo nome, para verificar se foi excluído
		this.autorRepository.delete(autorExcluido);
		autorExcluido = this.autorRepository.findByNome("TESTE DE EXCLUSÃO");
		assertThat(autorExcluido).isNull();
	}

	/*
	--------------------------------
		TESTE 'CRUD' DE LIVROS
	--------------------------------
	*/

	@Autowired
	LivroRepository livroRepository;

	/*
		TESTA A BUSCA DE UM LIVRO PELO NOME
	*/
	@Test
	public void testeBuscarLivroPorNome() {

		/*
		Busca o livro com nome 'Livro Ausente', que não consta no banco inicial, e verifica o resultado, que deve ser nulo
		*/
		Livro livroNaoEncontrado = this.livroRepository.findByNome("Livro Ausente");
		assertThat(livroNaoEncontrado).isNull();
		
		/*
		Busca o livro 'Dom Casmurro', que consta no banco inicial, e verifica o resultado, que deve ser 'não nulo', e a propriedade nome, que deve ser 'Machado de Assis'
		*/
		Livro livroEncontrado = this.livroRepository.findByNome("Dom Casmurro");
		assertThat(livroEncontrado).isNotNull();
		assertThat(livroEncontrado.getNome()).isEqualTo("Dom Casmurro");
	}

	/*
		TESTA A ALTERAÇÃO DO NOME DE UM LIVRO
	*/
	@Test
	public void testeAlterarNomeLivro() {

		// Busca o livro com nome 'Dom Casmurro', que consta no banco inicial, altera o nome para 'Dom Casmurro <ALTERADO>' e armazena a ID para comparação posterior
		Livro livroTeste = this.livroRepository.findByNome("Dom Casmurro");
		Long livroTesteId = livroTeste.getId();

		// Altera o nome do livroTeste 'Machado de Assis' para 'Machado de Assis <ALTERADO>' e salva no banco
		livroTeste.setNome("Dom Casmurro <ALTERADO>");
		this.livroRepository.save(livroTeste);

		// Busca o livro com a Id de 'livroTeste', que deve se referir ao mesmo registro no banco
		Livro livroTesteConfirma = this.livroRepository.findOne(livroTesteId);

		// Verifica se os nomes de 'livroTeste' e 'livroTesteAlterado' são iguais
		assertThat(livroTesteConfirma.getNome()).isEqualTo(livroTeste.getNome());

		// Restaura o nome original para não alterar o banco
		livroTeste.setNome("Dom Casmurro");
		this.livroRepository.save(livroTeste);
	}

	/*
		TESTA A INCLUSÃO DE UM NOVO LIVRO
	*/
	@Test
	public void testeIncluirLivro() {

		// Cria um novo livro com nome 'TESTE DE INCLUSÃO' e insere no banco
		Livro livroInserido = new Livro();
			livroInserido.setNome("TESTE DE INCLUSÃO");
			livroInserido.setQuantidadePaginas(100l);

		this.livroRepository.save(livroInserido);

		// Verifica se há no banco um livro com o nome 'TESTE DE INCLUSÃO', que é o que acabamos de inserir
		Livro livroEncontrado = this.livroRepository.findByNome("TESTE DE INCLUSÃO");
		assertThat(livroEncontrado).isNotNull();

		// Remove o livro incluido no teste, para que o banco retorne ao estado inicial
		this.livroRepository.delete(livroEncontrado);
	}

	/*
		TESTA A EXCLUSÃO DE UM LIVRO
	*/
	@Test
	public void testeExcluirLivro() {

		// Cria um novo livro com nome 'TESTE DE EXCLUSÃO' e insere no banco
		Livro livroExcluido = new Livro();
			livroExcluido.setNome("TESTE DE EXCLUSÃO");
			livroExcluido.setQuantidadePaginas(100l);

		this.livroRepository.save(livroExcluido);

		// Verifica se há no banco um livro com o nome 'TESTE DE EXCLUSÃO', que é o que acabamos de inserir
		livroExcluido = this.livroRepository.findByNome("TESTE DE EXCLUSÃO");
		assertThat(livroExcluido).isNotNull();

		// Remove o livro incluido no teste e faz uma busca de livro pelo nome, para verificar se foi excluído
		this.livroRepository.delete(livroExcluido);
		livroExcluido = this.livroRepository.findByNome("TESTE DE EXCLUSÃO");
		assertThat(livroExcluido).isNull();
	}

	/*
	------------------------------------
		TESTE 'CRUD' DE EMPRÉSTIMOS
	------------------------------------
	*/

	@Autowired
	EmprestimoRepository emprestimoRepository;

	/*
		TESTA A INCLUSÃO DE UM NOVO EMPRÉSTIMO
	*/
	@Test
	public void testeIncluirEmprestimo() {

		// Verifica se não há no banco um empréstimo com a data de retirada igual a '01/01/1900'
		assertThat(this.emprestimoRepository.findByDataEmprestimo("01/01/1900")).isNull();

		// Cria um novo empréstimo com a data de retirada igual a '01/01/1900' e insere no banco
		Emprestimo emprestimoInserido = new Emprestimo();
			emprestimoInserido.setDataEmprestimo("01/01/1900");
			emprestimoInserido.setDataDevolucao("01/02/1900");

		this.emprestimoRepository.save(emprestimoInserido);

		// Verifica se agora há no banco um empréstimo com a data de retirada igual a '01/01/1900', que é o que acabamos de inserir
		Emprestimo emprestimoEncontrado = this.emprestimoRepository.findByDataEmprestimo("01/01/1900");
		assertThat(emprestimoEncontrado).isNotNull();

		// Remove o empréstimo incluido no teste, para que o banco retorne ao estado inicial
		this.emprestimoRepository.delete(emprestimoEncontrado);
	}

	/*
		TESTA A EXCLUSÃO DE UM EMPRÉSTIMO
	*/
	@Test
	public void testeExcluirEmprestimo() {

		// Verifica se não há no banco um empréstimo com a data de retirada igual a '01/01/1900'
		assertThat(this.emprestimoRepository.findByDataEmprestimo("01/01/1900")).isNull();

		// Cria um novo empréstimo com a data de retirada igual a '01/01/1900' e insere no banco
		Emprestimo emprestimoExcluido = new Emprestimo();
			emprestimoExcluido.setDataEmprestimo("01/01/1900");
			emprestimoExcluido.setDataDevolucao("01/02/1900");

		this.emprestimoRepository.save(emprestimoExcluido);

		// Verifica se há no banco um empréstimo com a data de retirada '01/01/1900', que é o que acabamos de inserir
		emprestimoExcluido = this.emprestimoRepository.findByDataEmprestimo("01/01/1900");
		assertThat(emprestimoExcluido).isNotNull();

		// Remove o empréstimo incluido no teste e faz uma busca de empréstimo pela data de retirada, para verificar se foi excluído
		this.emprestimoRepository.delete(emprestimoExcluido);
		emprestimoExcluido = this.emprestimoRepository.findByDataEmprestimo("01/01/1900");
		assertThat(emprestimoExcluido).isNull();
	}

	/*
		TESTA A ALTERAÇÃO DA DATA DE ENTREGA DE UM EMPRÉSTIMO
	*/
	@Test
	public void testeAlterarDataDevolucaoEmprestimo() {

		/*
			Busca, pela Id, o empréstimo com data de devolução '08/17/2018', que consta no banco inicial
			Verifica se a data de devolução é igual a '08/17/2018'
		*/
		Emprestimo emprestimoTeste = this.emprestimoRepository.findOne(2l);
		assertThat(emprestimoTeste.getDataDevolucao()).isEqualTo("08/17/2018");

		/*
			Altera a data de devolução para '08/20/2018'
		*/
		emprestimoTeste.setDataDevolucao("08/20/2018");
		this.emprestimoRepository.save(emprestimoTeste);

		/*
			Busca o empréstimo com a Id '2', que deve se referir ao registro que alteramos
			Verifica se as datas de devolução do iguais
		*/
		Emprestimo emprestimoTesteVerifica = this.emprestimoRepository.findOne(2l);
		assertThat(emprestimoTeste.getDataDevolucao()).isEqualTo(emprestimoTesteVerifica.getDataDevolucao());

		// Restaura a data original para não alterar o banco
		emprestimoTesteVerifica.setDataDevolucao("08/17/2018");
		this.emprestimoRepository.save(emprestimoTesteVerifica);
	}
}
