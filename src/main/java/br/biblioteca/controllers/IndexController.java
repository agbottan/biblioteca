package br.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
	@GetMapping(value= {"/", "/index"})
	public ModelAndView home() {
		return new ModelAndView("index");
	}

    @GetMapping("/teste")
	public ModelAndView teste() {

		ModelMap map = new ModelMap();
		map.put("message", "Baeldung");
		return new ModelAndView("teste", map);
	}
}