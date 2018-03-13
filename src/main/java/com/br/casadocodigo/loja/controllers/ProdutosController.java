package com.br.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.models.Produto;
import com.br.casadocodigo.loja.models.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	/*@RequestMapping("/produtos/produtosform")
	public String ProdutosRequisicao(){
		return "produtos/produtosform";
	}*/
	//Modificado para retornar uma lista para página
	@RequestMapping("/produtos/produtosform")
	public ModelAndView ProdutosRequisicao(){
		ModelAndView mv = new ModelAndView("produtos/produtosform");
		//tipo = id que será enviado para view
		mv.addObject("tipos", TipoPreco.values());
		return mv;
	}
	
	/*Melhorado este Método
	@RequestMapping("/produtos")
	public void gravar(String titulo, String descricao, int paginas){
		System.out.println(titulo + " - " + descricao + " - " + paginas);
	}*/
	
	@RequestMapping("/produtos")
	public String gravar(Produto produto){
		System.out.println(produto);
		produtoDao.gravar(produto);
		return "produtos/ok";
	}
}
