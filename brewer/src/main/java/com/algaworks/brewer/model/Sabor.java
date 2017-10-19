package com.algaworks.brewer.model;

public enum Sabor {

	ADOCICADA("Adocicada"),
	AMARGA("Amarga"),
	FORTE("Forte"),
	FRUTADA("Frutada"),
	SUAVE("Suave");
	
	private String sabor;
	
	Sabor(String sabor) {
		this.sabor = sabor;
	}
	
	public String getSabor() {
		return sabor;
	}
	
}
