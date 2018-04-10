package com.br.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.casadocodigo.loja.models.CarrinhoCompras;
import com.br.casadocodigo.loja.models.DadosPagamento;
import com.br.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	@Autowired
	CarrinhoCompras carrinhoCompras;
	@Autowired
	RestTemplate rest;
	@Autowired
	MailSender emailSender;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	//public ModelAndView finalizar(RedirectAttributes model){
	//public Callable<ModelAndView> finalizar(RedirectAttributes model){
	public Callable<ModelAndView> finalizar( @AuthenticationPrincipal Usuario usuario, RedirectAttributes model){  // modificado para envio de email
		return () -> {
			try{
				String url = "http://book-payment.herokuapp.com/payment";
				String response = rest.postForObject(url, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
				System.out.println(carrinhoCompras.getTotal());
				
				enviaEmailCompraProduto(usuario);
				
				model.addFlashAttribute("retorno", response);
				return new ModelAndView("redirect:/produtos");
			} catch ( Exception e) {
			        e.printStackTrace();
			        model.addFlashAttribute("falha", "Valor maior que o permitido");
			        return new ModelAndView("redirect:/produtos");
			}
		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra Finalizada com sucesso!" );
		email.setTo("joaovictor2@hotmail.com");
		email.setText("Compra realizada no valor de R$" + carrinhoCompras.getTotal());
		email.setFrom("joaovictor2@gmail.com");
		
		emailSender.send(email);
	}
}

