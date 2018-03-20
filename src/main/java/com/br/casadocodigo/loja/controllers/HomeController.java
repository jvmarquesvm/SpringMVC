package com.br.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {

	/*
	 * @RequestMapping("/") //public void index(){ public String index(){
	 * System.out.println("Entrando na home CDC"); return "home"; }
	 */
	@Autowired
	ProdutoDao produtoDao;

	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listarProduto();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
