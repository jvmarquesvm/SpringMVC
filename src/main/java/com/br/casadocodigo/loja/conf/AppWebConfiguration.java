package com.br.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.br.casadocodigo.loja.controllers.HomeController;
import com.br.casadocodigo.loja.daos.ProdutoDao;

@EnableWebMvc
//@ComponentScan(basePackages={"com.br.casadocodigo.loja.controllers"})
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDao.class})
public class AppWebConfiguration {
	
	//InternalResourceViewResolver fala ao spring o local das views
	//@Bean retorno da chamada deste método possa ser gerenciada pelo SpringMVC
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolverView = new InternalResourceViewResolver();
		resolverView.setPrefix("/WEB-INF/views/"); // Local das view
		resolverView.setSuffix(".jsp"); //extensões das views
		return resolverView;
	}
	
	//ReloadableResourceBundleMessageSource responsável pelas mensagens do sistema
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
		message.setBasename("/WEB-INF/messages");
		message.setDefaultEncoding("UTF-8");
		message.setCacheSeconds(5);
		return message;
	}
	
	//DefaultFormattingConversionService Responsável pela formatação da data do sistema
	@Bean
	public FormattingConversionService mvcConversionService(){
	    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
	    formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    formatterRegistrar.registerFormatters(conversionService);
	    return conversionService;
	}
}
