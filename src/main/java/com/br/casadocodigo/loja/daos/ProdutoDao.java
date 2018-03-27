package com.br.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.casadocodigo.loja.models.Produto;
import com.br.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDao {
	
	//Dizendo ao Spring para injetar o EntityManager
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto){
		manager.persist(produto);
	}
	
	public List<Produto> listarProduto(){
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto procura(Integer id) {
		//Traz o Produto
		//return manager.find(Produto.class, id);
		//Traz Produto e Preço
		return manager.createQuery("select distinct(p) from Produto p join fetch "
				                        + "  p.precos pe where p.id = :id", Produto.class)
				                        .setParameter("id", id)
				                        .getSingleResult();
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
	    TypedQuery<BigDecimal> query = manager.createQuery("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class);
	    query.setParameter("tipoPreco", tipoPreco);
	    return query.getSingleResult();
	}
}
