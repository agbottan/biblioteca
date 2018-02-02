package br.biblioteca.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.beans.User;
import br.biblioteca.services.SecurityService;
import br.biblioteca.services.UserService;
import br.biblioteca.validator.LoginValidator;
import br.biblioteca.validator.UserValidator;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private LoginValidator loginValidator;

	@GetMapping(value= {"", "/", "/login"})
	public ModelAndView login() {
		return new ModelAndView("user/form", "userForm", new User());
	}

	@PostMapping("/autentication")
	public ModelAndView autentication(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        // Valida formulário
		loginValidator.validate(userForm, bindingResult);
		
		// Já retorna se tem erros de validação
		if (bindingResult.hasErrors()) {
			return new ModelAndView("user/form");
		}

        // Login do Sprint.security
        Exception e = securityService.login(userForm.getUsername(), userForm.getPassword());
        
        // Erro
        if (e.getMessage() == "Bad credentials") {
            return new ModelAndView("redirect:/teste");
        };

        // Ok
        return new ModelAndView("redirect:/user/listar");
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		return new ModelAndView("/user/listar");
	}
	
	@GetMapping("/listadmin")
	public ModelAndView listadmin(User user) {

		List<User> users = userService.findAll();
		return new ModelAndView("/user/listadmin","users",users);
	}

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        
        return new ModelAndView("user/registration", "userForm", new User());
    }

	@PostMapping(value = "/registration")
	public ModelAndView registrationform(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("user/registration");
		}
		
		String password = userForm.getPassword();

		userService.save(userForm);

		try {
			securityService.login(userForm.getUsername(), password);
			return new ModelAndView("redirect:/user/listar");
			
		} catch (Exception e) {
			
			return new ModelAndView("redirect:/user/login");
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/user/login";
	}

}
