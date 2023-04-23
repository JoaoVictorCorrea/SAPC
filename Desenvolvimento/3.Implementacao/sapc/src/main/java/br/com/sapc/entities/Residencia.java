package br.com.sapc.entities;

import java.util.ArrayList;

public class Residencia {
	
	private Integer numero;
	private String bloco;
	
	private ArrayList<Morador> moradores;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public ArrayList<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(ArrayList<Morador> moradores) {
		this.moradores = moradores;
	} 
	
	
}
