/*
package br.biblioteca.controllers;

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

import br.biblioteca.beans.Usuario;
import br.biblioteca.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@GetMapping("/")
    public ModelAndView usuarios() {

        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        return new ModelAndView("usuarios/listar", "usuarios", usuarios);
    }
	
	
	@GetMapping("/novo")
    public String createForm(@ModelAttribute Usuario usuario) {
        return "usuarios/form";
    }
	
	@PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuarios/form");
        }

        usuario = usuarioRepository.save(usuario);
            return new ModelAndView("redirect:/usuarios/");
    }
	
	@GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        Usuario usuario = this.usuarioRepository.findOne(id);
        return new ModelAndView("usuarios/form", "usuario", usuario);
    }
	
	@GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Usuario usuario = this.usuarioRepository.findOne(id);
        this.usuarioRepository.delete(usuario);
        return new ModelAndView("redirect:/usuarios/");
	}
}
*/