package br.com.sapc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity
public class Morador extends Pessoa{
	
	private boolean sindico;
	private String senha;
	
	@JoinColumn(nullable = false, name = "codigo_residencia")
	private Residencia residencia;

	public boolean isSindico() {
		return sindico;
	}

	public void setSindico(boolean sindico) {
		this.sindico = sindico;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}
	
	
}
