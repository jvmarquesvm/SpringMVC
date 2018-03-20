package com.br.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class DadosPagamento implements Serializable {
	private static final long serialVersionUID = -8451993761326952170L;
	private BigDecimal value;

	    public DadosPagamento(BigDecimal value) {
	        this.value = value;
	    }

	    public DadosPagamento() {
	    }

	    public BigDecimal getValue() {
	        return value;
	    }
}
