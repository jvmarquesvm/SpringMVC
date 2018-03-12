package com.br.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.casadocodigo.loja.models.Produtos;

@Controller
public class ProdutosController {
	
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
	public String gravar(Produtos produto){
		System.out.println(produto);
		return "produtos/ok";
	}
}
