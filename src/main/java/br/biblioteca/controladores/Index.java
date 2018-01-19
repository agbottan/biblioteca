package br.biblioteca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.beans.Livro;
import br.biblioteca.repository.LivroRepository;

@Controller
public class Index {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index");
	}
	
    @GetMapping("/livros")
    public ModelAndView livros() {
        Iterable<Livro> livros = livroRepository.findAll();
        return new ModelAndView("livros", "livros", livros);
    }

    @GetMapping("/teste")
	public ModelAndView teste() {
		return new ModelAndView("teste");
	}
}
