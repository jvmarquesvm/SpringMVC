package com.br.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/produtos/produtosform")
	public String ProdutosRequisicao(){
		return "produtos/produtosform";
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
