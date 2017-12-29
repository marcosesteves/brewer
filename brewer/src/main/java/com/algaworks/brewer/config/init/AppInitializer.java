package com.algaworks.brewer.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.algaworks.brewer.config.JPAConfig;
import com.algaworks.brewer.config.ServiceConfig;
import com.algaworks.brewer.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {  // As anotações @Configuration devem ser declaradas aqui

	@Override // Configurações para localização de Beans  que devem estar disponíveis para os servlets 
	protected Class<?>[] getRootConfigClasses() { // um array com um objeto Class de uma classe de Configuração que informa ao Spring como achar Entidades e Controllers
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class };  // Para o Spring encontrar as classes de configuração. Ele extrai informações de localização do .class das classes *Configuration
	}

	@Override  // Configurações de Beans que envolvem Web como servlets
	protected Class<?>[] getServletConfigClasses() { // um array com um objeto Class de uma classe de Configuração que informa ao Spring como achar Entidades e Controllers
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; // com a "/" Qualquer requisição a partir do Contexto será encaminhada para o DispatcherServlet 
	}
	
	@Override
	protected Filter[] getServletFilters() { // Configuração para forçar o encoder UTF-8 na geração da Wiew resultante
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[] { characterEncodingFilter };
}

}