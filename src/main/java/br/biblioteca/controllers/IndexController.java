package br.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
	@GetMapping(value= {"/", "/index"})
	public ModelAndView home() {
		return new ModelAndView("index");
	}

    @GetMapping("/teste")
	public ModelAndView teste() {
		return new ModelAndView("teste");
	}
}
