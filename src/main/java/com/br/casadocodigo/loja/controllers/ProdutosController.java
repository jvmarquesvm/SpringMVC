package com.br.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.infra.FileSaver;
import com.br.casadocodigo.loja.models.Produto;
import com.br.casadocodigo.loja.models.TipoPreco;
import com.br.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping(value="/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder bind){
		bind.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/produtosform")
	public ModelAndView produtosRequisicao(Produto produto){
		ModelAndView mv = new ModelAndView("produtos/produtosform");
		//tipo = id que será enviado para view
		mv.addObject("tipos", TipoPreco.values());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, 
			                        BindingResult resultado, RedirectAttributes reAttr ){
		if(resultado.hasErrors()){
			return produtosRequisicao(produto);
		}
		System.out.println(sumario.getOriginalFilename());
		String sumarioReal =  fileSaver.write("arquivos-sumario", sumario);	
		produto.setSumarioPath(sumarioReal);
		
		produtoDao.gravar(produto);
		
		reAttr.addFlashAttribute("retorno", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping( method=RequestMethod.GET)
	public ModelAndView listar(){
		List<Produto> produtos = produtoDao.listarProduto();
		ModelAndView mv = new ModelAndView("produtos/produtosformlist");
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	//@RequestMapping("/detalhe")
	@RequestMapping("/detalhe/{id}")
	//public ModelAndView find(int id){
	public ModelAndView find( @PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView("/produtos/detalhe");
		Produto produto = produtoDao.procura(id);
		mv.addObject("produto", produto);
		return mv;
	}

}
