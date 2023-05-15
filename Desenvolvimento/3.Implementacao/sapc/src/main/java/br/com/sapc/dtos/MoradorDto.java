package br.com.sapc.dtos;

import java.time.LocalDate;

import br.com.sapc.entities.Residencia;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MoradorDto {

	private String nome;
	
	@Email(message = "Email inválido")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Endereço de e-mail inválido")
	@NotBlank
	private String email;
	
	@Size(min = 11, max = 11)
	private String cpf;
	
	@NotNull
	private LocalDate dataNasc;
	
	@Size(min = 10, max = 11)
	@NotBlank
	private String telefone;
	
	@NotNull
	private Residencia residencia;
	
	@NotNull
	private Boolean sindico;

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

	public Boolean getSindico() {
		return sindico;
	}

	public void setSindico(Boolean sindico) {
		this.sindico = sindico;
	}
	
	
}
