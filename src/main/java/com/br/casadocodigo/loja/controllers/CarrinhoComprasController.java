package com.br.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.models.CarrinhoCompras;
import com.br.casadocodigo.loja.models.CarrinhoItem;
import com.br.casadocodigo.loja.models.Produto;
import com.br.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens(){
	    return new ModelAndView("/carrinho/itens");
	}
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco){
		ModelAndView mv = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinhoCompras.add(carrinhoItem);
		return mv;
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco){
		Produto produto = produtoDao.procura(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco){
		carrinhoCompras.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
}
	

