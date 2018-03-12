package com.br.casadocodigo.loja.daos;

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
}