package com.algaworks.brewer.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import com.algaworks.brewer.controller.CervejasController;

@Configuration
@ComponentScan(basePackageClasses = { CervejasController.class }) // Poderia ser passado uma String com o pacote onde se encontra a classe responsável pelos Controllers (com a anotação Controller). Como opção pode-se passar o atributo BasePackageClass um array com o(s) .class da(s) classe(s) de onde o pacote poderá ser recuperado
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { // Metodo que retorna o ApplicationContext
		this.applicationContext = applicationContext;
	}

	@Bean // Faz com que SpringTemplateEngine fique disponível para toda a aplicação 
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());  // Precisa do TemplateEngine
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean // Faz com que SpringTemplateEngine fique disponível para toda a aplicação 
	public TemplateEngine templateEngine() { // Engine capaz de processar as Views com dados feito pelo Thymeleaf (conversao das tags th:...)
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());  // Precisa do ITemplateResolver 
		return engine;
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext); // Necessita do ApplicationContext fornecido pela interface ApplicationContextAware
		resolver.setPrefix("classpath:/templates/"); // Informa onde ficam os templates ao ViewResolver
		resolver.setSuffix(".html"); // Informa o sufixo dos templates ao ViewResolver
		resolver.setTemplateMode(TemplateMode.HTML); // Informa o tipo das Views ao ViewResolver
		return resolver;
	}

}