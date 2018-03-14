package com.br.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.models.Produto;
import com.br.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping(value="produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	/*@RequestMapping("/produtos/produtosform")
	public String ProdutosRequisicao(){
		return "produtos/produtosform";
	}*/
	//Modificado para retornar uma lista para página
	@RequestMapping("/produtosform")
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
	
	//método GET
	//@RequestMapping(value="/produtos", method=RequestMethod.POST)
	@RequestMapping(method=RequestMethod.POST)
	public String gravar(Produto produto){
		System.out.println(produto);
		produtoDao.gravar(produto);
		return "produtos/ok";
	}
	
	//método POST --Assim ou
	@RequestMapping( method=RequestMethod.GET)
	public ModelAndView listar(){
		List<Produto> produtos = produtoDao.listarProduto();
		ModelAndView mv = new ModelAndView("produtos/produtosformlist");
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	//Assim
    /*@RequestMapping("produtos")
    public String lista(Model model) {
        List<Produto> produtos = produtoDAO.listar();
        model.addAttribute("produtos", produtos);
        return "produtos/lista";
    }*/
}
