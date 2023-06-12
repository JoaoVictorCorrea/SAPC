package br.com.sapc.entities;

import java.time.LocalDateTime;

import br.com.sapc.enums.StatusSolicitacao;
import br.com.sapc.enums.TipoUsuarioExterno;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_solicitacaodeentrada")
public class SolicitacaoDeEntrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataSolicitacao;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuarioExterno tipoUsuarioExterno;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "residencia_id")
	private Residencia residencia;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "usuario_externo_id")
	private UsuarioExterno usuarioExterno;
	
	@Enumerated(EnumType.STRING)
	private StatusSolicitacao statusSolicitacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public TipoUsuarioExterno getTipoUsuarioExterno() {
		return tipoUsuarioExterno;
	}

	public void setTipoUsuarioExterno(TipoUsuarioExterno tipoUsuarioExterno) {
		this.tipoUsuarioExterno = tipoUsuarioExterno;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	public UsuarioExterno getUsuarioExterno() {
		return usuarioExterno;
	}

	public void setUsuarioExterno(UsuarioExterno usuarioExterno) {
		this.usuarioExterno = usuarioExterno;
	}

	public StatusSolicitacao getStatusSolicitacao() {
		return statusSolicitacao;
	}

	public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}
	
}
