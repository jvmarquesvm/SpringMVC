package com.br.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.br.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDao  implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	//public Usuario find(String email){
	public UserDetails loadUserByUsername(String email){	
			List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
					.setParameter("email", email)
					.getResultList();
			if (usuarios.isEmpty()){
				throw new UsernameNotFoundException ("O usuario " + email + " não foi encontrado.");
			}
			
			return usuarios.get(0);
	}
}
