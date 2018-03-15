package com.br.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.br.casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator {
	//Classe para indicar ao Spring se estou dando suporte de validação a Classe Target
	@Override
	public boolean supports(Class<?> classe) {
		return Produto.class.isAssignableFrom(classe);
	}

	@Override
	public void validate(Object classeAlvo, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		
		Produto produto = (Produto) classeAlvo;
		if (produto.getPaginas() <= 0){
			errors.rejectValue("paginas", "field.required");
		}
	}
}
