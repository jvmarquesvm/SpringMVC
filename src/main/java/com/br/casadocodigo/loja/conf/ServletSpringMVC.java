package com.br.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Classe de Inicialização da Aplicação
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfiguration.class , AppWebConfiguration.class, 
				                  JPAConfiguration.class,JPAProductionConfiguration.class};
	}

	//AppWebConfiguration sera a classe de configuracão do projeto
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
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
		return new Filter[]{charencoding, new OpenEntityManagerInViewFilter()} ; //Adicionado o filtro para manter aberto até a visualização na tela
	}
	
	//Fazer a conversão dos arquivos para o Spring Gerenciá-lo
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	//Ouvinte de Contexto para saber se o profile é dev
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    super.onStartup(servletContext);
	    servletContext.addListener(new RequestContextListener());
	    servletContext.setInitParameter("spring.profiles.active", "dev");
	}
	
}
