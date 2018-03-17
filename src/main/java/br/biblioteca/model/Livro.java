package br.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Livro {
	
	@Id
	@GeneratedValue
	private Long id;
	
    @NotNull
    @Size(min = 2, max = 100)
	private String nome;
	
	private String foto;

    @NotNull
    @Min(10)
	private Long quantidadePaginas;
	
    @Transient
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;

	@OneToMany(mappedBy="livro")
	private List<Emprestimo> emprestimos = new ArrayList<>();
		
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Long getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(Long quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}
	
    @Override
    public String toString() {
        return "Livro [id=" + id + ", nome=" + nome + ", quantidadePaginas=" + quantidadePaginas + "]";
    }
}
