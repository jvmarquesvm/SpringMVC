package com.br.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.casadocodigo.loja.models.Produto;

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

	public Produto procura(int id) {
		//Traz o Produto
		//return manager.find(Produto.class, id);
		//Traz Produto e Preço
		return manager.createQuery("select distinct(p) from Produto p join fetch "
				                        + "  p.precos pe where p.id = :id", Produto.class)
				                        .setParameter("id", id)
				                        .getSingleResult();
	}
}
