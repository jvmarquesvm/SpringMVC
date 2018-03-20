package com.br.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.casadocodigo.loja.models.CarrinhoCompras;
import com.br.casadocodigo.loja.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	@Autowired
	CarrinhoCompras carrinhoCompras;
	@Autowired
	RestTemplate rest;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	//public ModelAndView finalizar(RedirectAttributes model){
	public Callable<ModelAndView> finalizar(RedirectAttributes model){
		return () -> {
			try{
				String url = "http://book-payment.herokuapp.com/payment";
				String response = rest.postForObject(url, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
				System.out.println(carrinhoCompras.getTotal());
				model.addFlashAttribute("retorno", response);
				return new ModelAndView("redirect:/produtos");
			} catch ( Exception e) {
			        e.printStackTrace();
			        model.addFlashAttribute("falha", "Valor maior que o permitido");
			        return new ModelAndView("redirect:/produtos");
			}
		};
	}
}

