package com.br.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
		
	@RequestMapping("/")
	//public void index(){
	public String index(){
		System.out.println("Entrando na home CDC");
		return "home";
	}
}
