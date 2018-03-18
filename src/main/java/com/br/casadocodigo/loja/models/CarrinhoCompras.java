package com.br.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras implements Serializable {
	
	private static final long serialVersionUID = -519143732306998288L;
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem carrinhoItem){
		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);
	}
	
	public int getQuantidade(CarrinhoItem item){
		//verifica se não tem na lista tem a chave, que no caso é o 
		 //item do carrinho e utilizar o método equals
		if (!itens.containsKey(item)){
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public Integer getQuantidade(){
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	
	public Collection<CarrinhoItem> getItens(){
		return itens.keySet();
	}
	
	public BigDecimal getTotal(CarrinhoItem item){
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal(){
	    BigDecimal total = BigDecimal.ZERO;
	    for (CarrinhoItem item : itens.keySet()) {
	        total = total.add(getTotal(item));
	    }
	    return total;
	}
	

}
