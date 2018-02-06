package br.biblioteca.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.model.Autor;
import br.biblioteca.model.Emprestimo;
import br.biblioteca.model.Livro;
import br.biblioteca.repository.AutorRepository;
import br.biblioteca.repository.LivroRepository;
import br.biblioteca.repository.EmprestimoRepository;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
    private EmprestimoRepository emprestimoRepository;
	
	@Autowired
    private LivroRepository livroRepository;

	@GetMapping(value= {"", "/", "/listar"})
    public ModelAndView emprestimos() {

        Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return new ModelAndView("emprestimos/listar", "emprestimos", emprestimos);
    }
	
	@GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Emprestimo emprestimo) {

		ModelAndView modelAndView = new ModelAndView("emprestimos/form");
        
        Iterable<Livro> livros = livroRepository.findAll();
        modelAndView.addObject("livros", livros);

        return modelAndView;
    }
	
	@PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("emprestimo") @Valid Emprestimo emprestimo, BindingResult bindingResult) {

    	/*
    	String sDate1="31/12/1998";  
    	Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
    	System.out.println(sDate1+"\t"+date1);  

    	System.out.println("*****************************");
    	System.out.println(bindingResult);
    	System.out.println("*****************************");
    	*/

    	if (bindingResult.hasErrors()) {
    		// System.out.println("********************* não");
            return new ModelAndView("emprestimos/form");
        }

        emprestimo = emprestimoRepository.save(emprestimo);
    	// System.out.println("********************* sim");
		return new ModelAndView("redirect:/emprestimos/");
    }
	
	@GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {

        Emprestimo emprestimo = this.emprestimoRepository.findOne(id);

        ModelAndView modelAndView = new ModelAndView("emprestimos/form");
        modelAndView.addObject("emprestimo", emprestimo);

        // Livro
		Iterable<Livro> livros = livroRepository.findAll();
        modelAndView.addObject("livros", livros);
        
        return modelAndView;
    }
	
	@GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Emprestimo emprestimo = this.emprestimoRepository.findOne(id);
        this.emprestimoRepository.delete(emprestimo);
        return new ModelAndView("redirect:/emprestimos/");
	}
}
