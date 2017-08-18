package com.algaworks.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.algaworks.brewer.controller.CervejasController;

@Configuration // Informa que é uma classe de configuração
@ComponentScan(basePackageClasses = { CervejasController.class })  // Informa o pacote onde estão 
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {  // O Spring possui classes adaptadoras para auxiliar a configuração

}