package br.com.sapc.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_morador")
public class Morador{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome Completo inválido")
	private String nome;
	
	@Column(unique = true)
	@NotBlank(message = "Endereço de e-mail inválido")
	private String email;
	
	@Column(unique = true)
	@NotBlank(message = "Cpf inválido")
	@Size(min = 11, max = 11, message = "Cpf inválido")
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Data de Nascimento inválida")
	private LocalDate dataNasc;
	
	@Column(unique = true)
	@NotBlank(message = "Telefone inválido")
	@Size(min = 10, max = 11, message = "Telefone inválido")
	private String telefone;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "residencia_id")
	@Valid
	private Residencia residencia;
	
	@NotNull(message = "Escolha uma opção válida")
	private Boolean sindico;
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSindico() {
		return sindico;
	}

	public void setSindico(Boolean sindico) {
		this.sindico = sindico;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}
}

