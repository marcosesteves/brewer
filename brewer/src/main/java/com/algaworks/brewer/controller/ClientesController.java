package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.algaworks.brewer.model.Cerveja;


@Controller
public class ClientesController {
	
	@RequestMapping("/clientes/novo")
	public String novo(Cerveja cerveja) { 
		return "cliente/CadastroCliente";
	}

}