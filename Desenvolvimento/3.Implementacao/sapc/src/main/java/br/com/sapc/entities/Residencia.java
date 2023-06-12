package br.com.sapc.entities;

import java.util.List;

import br.com.sapc.enums.TipoBloco;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_residencia")
public class Residencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Número da residência inválido")
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Bloco inválido")
	private TipoBloco bloco;
	
	@OneToMany(mappedBy = "residencia")
	private List<Morador> moradores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoBloco getBloco() {
		return bloco;
	}

	public void setBloco(TipoBloco bloco) {
		this.bloco = bloco;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	
	
	
}