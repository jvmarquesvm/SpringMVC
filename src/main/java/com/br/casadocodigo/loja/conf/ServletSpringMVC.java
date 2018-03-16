package com.br.casadocodigo.loja.conf;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Classe de Inicialização da Aplicação
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	//AppWebConfiguration sera a classe de configuracão do projeto
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}
	
	//O Spring atenderá requisições através da pasta raiz
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};	
	}
	
	//Configura o Spring para enconding UTF-8
	@Override
	protected Filter[] getServletFilters(){
		CharacterEncodingFilter charencoding = new CharacterEncodingFilter();
		charencoding.setEncoding("UTF-8");
		return new Filter[]{charencoding} ;
	}
	
	//Fazer a conversão dos arquivos para o Spring Gerenciá-lo
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
