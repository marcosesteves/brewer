package com.algaworks.brewer.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.algaworks.brewer.controller.CervejasController;
import com.algaworks.brewer.controller.converter.EstiloConverter;
import com.algaworks.brewer.model.Estilo;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = { CervejasController.class }) // Poderia ser passado uma String com o pacote onde se encontra a classe responsável pelos Controllers (com a anotação Controller). Como opção pode-se passar o atributo BasePackageClass um array com o(s) .class da(s) classe(s) de onde o pacote poderá ser recuperado
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { // Metodo que disponibiliza o ApplicationContext na classe WebConfig
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
		engine.setTemplateResolver(templateResolver());  // Precisa do TemplateResolver
		engine.addDialect(new LayoutDialect());  // Adiciona a classe do Thymeleaf LayoutDialect para o uso de templates nas Views
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
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  // Metodo para informar onde estão os recursos que não estão mapeados pelo controller como css, javascript, imagens ... 
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {   // Utilizado para registrar no Spring um Converter que é usado para converter um tipo em outro automaticamente pelo Spring -- O método tem que ter esse nome --
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(new EstiloConverter());  // Sem Lambda
		return conversionService;
		
		// ??????? Não sei porque não funciona!!!
/*		Converter<String, Estilo> conversor = ( String codigo ) -> {
			if (!StringUtils.isEmpty(codigo)) {
				Estilo estilo = new Estilo();
				estilo.setCodigo(Long.valueOf(codigo));
				return estilo;
			}
			return null;
		};
		conversionService.addConverter(conversor);
		return conversionService;
	}
*/	

/*		
		conversionService.addConverter(codigo -> 
		{
			if (!StringUtils.isEmpty(codigo)) {
				Estilo estilo = new Estilo();
				estilo.setCodigo(Long.valueOf((String)codigo));
				return estilo;
			}
			
			return null;
		});
		return conversionService;
	}
*/
	}
}