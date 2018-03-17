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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.br.casadocodigo.loja.controllers.HomeController;
import com.br.casadocodigo.loja.daos.ProdutoDao;
import com.br.casadocodigo.loja.infra.FileSaver;
import com.br.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc
//@ComponentScan(basePackages={"com.br.casadocodigo.loja.controllers"})
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDao.class, 
		                                    FileSaver.class, CarrinhoCompras.class} )
public class AppWebConfiguration  extends WebMvcConfigurerAdapter {
	
	//InternalResourceViewResolver fala ao spring o local das views
	//@Bean retorno da chamada deste método possa ser gerenciada pelo SpringMVC
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolverView = new InternalResourceViewResolver();
		resolverView.setPrefix("/WEB-INF/views/"); // Local das view
		resolverView.setSuffix(".jsp"); //extensões das views
		
		//Necessario para a quantidade de itens no carrinho de compras
		//resolverView.setExposeContextBeansAsAttributes(true);//Expoem todos os Beans como atributos
		resolverView.setExposedContextBeanNames("carrinhoCompras"); 
		// Expoem apenas o Bean que se quer trabalhar - primeira letra minuscula
		
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
	
	//Configurar o envio de arquivos para o Spring
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	//Se não fizer isso, não vai buscar nenhuma configuração CSS ou imagens herdando de WebMvcConfigurerAdapter
	//Arquivos de CSS devem estar no webapp/resources
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
}
