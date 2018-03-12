package com.br.casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	//AppWebConfiguration sera a classe de configuracão do projeto
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class};
	}
	
	//O Spring atenderá requisições através da pasta raiz
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};	
	}

}
