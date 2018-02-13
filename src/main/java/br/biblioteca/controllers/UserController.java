package br.biblioteca.controllers;

import br.biblioteca.model.User;
import br.biblioteca.repository.UserRepository;
import br.biblioteca.services.SecurityService;
import br.biblioteca.services.UserService;
import br.biblioteca.validator.LoginValidator;
import br.biblioteca.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

   	@Autowired
	private LoginValidator loginValidator;


///////// REGISTRAR USUÁRIO

	// Acesso à página
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration(Model model) {

		return new ModelAndView("user/registration", "userForm", new User());
	}

	// Registro
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("user/registration");
		}
		
		String password = userForm.getPassword();
		userService.save(userForm);

		try {
			securityService.autologin(userForm.getUsername(), password);
			return new ModelAndView("redirect:/user/listar");
			
		} catch (Exception e) {
			return new ModelAndView("redirect:/user/login");
		}
	}

///////////////////////////

	@RequestMapping(value = "/user/listar", method = RequestMethod.GET)
	public ModelAndView listar() {

		Iterable<User> listaUsers = userRepository.findAll();

		return new ModelAndView("user/listar", "listaUsers", listaUsers);
	}

	@RequestMapping(value = "/login-erro", method = RequestMethod.GET)
	public ModelAndView loginErro() {

		return new ModelAndView("user/login-erro");
	}


///////// LOGIN

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return new ModelAndView("user/form", "userForm", new User());
    }

	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ModelAndView authentication(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		
		loginValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("user/form");
		}
		
		securityService.autologin(userForm.getUsername(), userForm.getPassword());
		return new ModelAndView("redirect:/user/listar");
	}


///////// LOGOUT

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {

		return new ModelAndView("user/logout");
	}
}
