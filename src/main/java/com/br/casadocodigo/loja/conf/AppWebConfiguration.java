package com.br.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.br.casadocodigo.loja.controllers.HomeController;

@EnableWebMvc
//@ComponentScan(basePackages={"com.br.casadocodigo.loja.controllers"})
@ComponentScan(basePackageClasses={HomeController.class})
public class AppWebConfiguration {
	
	//InternalResourceViewResolver fala ao spring o local das views
	//@Bean retorno da chamada deste metódo possa ser gerenciada pelo SpringMVC
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolverView = new InternalResourceViewResolver();
		resolverView.setPrefix("/WEB-INF/views/"); // Local das view
		resolverView.setSuffix(".jsp"); //extensão das views
		return resolverView;
	}

}
