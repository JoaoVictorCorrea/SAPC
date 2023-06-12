package br.com.sapc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sapc.entities.SolicitacaoDeEntrada;

@Repository
public interface SolicitacaoDeEntradaRepository extends JpaRepository<SolicitacaoDeEntrada, Long>{

	@Query("select count(s) from SolicitacaoDeEntrada s where s.usuarioExterno.id = :id and s.statusSolicitacao = 'PENDENTE'")
	int qtdSolicitacoesPendentes(@Param("id") Long id);

}
