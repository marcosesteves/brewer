package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;  // Logger do pacote do slf4j
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	public static final Logger logger = LoggerFactory.getLogger(CervejasController.class);

	@RequestMapping("/cervejas/novo")
	// public String novo(Model model) { Recebendo o Model como parâmetro
	public ModelAndView novo(Cerveja cerveja) { // Pode não definir o Model que o
											// Spring reconhece que um objeto
											// cerveja deve ser criado e
											// retornado a view;
		// model.addAttribute("outroNome", new Cerveja()); ---> Caso fosse usar
		// um nome de obj diferente da classe a ser passado no Model
		// model.addAttribute(new Cerveja()); ---> Usando o Model
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}

	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result,	/* Model model, */ RedirectAttributes attributes) {
		if (result.hasErrors()) {
			// model.addAttribute(cerveja); // Com o forward (default) não há
			// nova requisição e o uso do Model permanece até a página
			// solicitada.
			// return "cerveja/CadastroCerveja";
			return novo(cerveja); // Essa opção é para não repetir o código do
									// método novo(Cerveja cerveja) acima. Chama
									// novo() passando a cerveja e não precisa
									// declarar o Model como parâmetro
		}
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		System.out.println(">>> sku: " + cerveja.getSku());
		System.out.println(">>> Nome: " + cerveja.getNome());
		System.out.println(">>> Descrição: " + cerveja.getDescricao());
		System.out.println(">>> Sabor: " + cerveja.getSabor());
		System.out.println(">>> Origem: " + cerveja.getOrigem());
		
		System.out.println(">>> Estilo: " + cerveja.getEstilo().getCodigo());
		
		return new ModelAndView("redirect:/cervejas/novo"); // Com o Redirect é feito uma nova
											// requisição do tipo GET e o Model
											// não permaneceria até a página
											// direcionada. Com o
											// RedirectAttributes é criado uma
											// sessão temporária para manter o
											// atributo.
	}
}