package br.com.keepcred.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.keepcred.entities.Prestamistas;

public interface PrestamistasRepository extends JpaRepository<Prestamistas, Long> {

	@Query(value = "SELECT b.descnomecliente, b.numcpfcnpj, a.datanascimento, MIN(e.codtiposituacaotitulo) AS codtiposituacaotitulo, "
			+ "COUNT(DISTINCT c.numcontacorrente) AS numcontas, MAX(coalesce(d.valorlimite, 0)) AS valorlimite, "
			+ "SUM(CASE WHEN codtiposituacaotitulo = 1 THEN e.valorsaldodevedorcontabil ELSE 0 END) AS capitalsegurado "
			+ "FROM spu_cli_clientefisica a, spu_cli_clienteinstunidade b, spu_cco_participantecontacor c LEFT JOIN spu_cco_limitecredito d ON d.numcontacorrente = c.numcontacorrente, "
			+ "spu_cre_contratocredito e WHERE a.idcliente = b.idcliente AND a.idcliente = c.idcliente AND a.idcliente = e.idcliente "
			+ "GROUP BY a.idcliente ORDER BY b.descnomecliente", nativeQuery = true)
	List<Prestamistas> getPrestamistasComContrato();

	@Query(value = "SELECT b.descnomecliente, b.numcpfcnpj, a.datanascimento, d.valorlimite, 0.0 AS capitalsegurado, 1 AS NUMCONTAS, 2 AS codtiposituacaotitulo "
			+ "FROM spu_cli_clientefisica a, spu_cli_clienteinstunidade b, spu_cco_participantecontacor c LEFT JOIN spu_cco_limitecredito d ON d.numcontacorrente = c.numcontacorrente "
			+ "WHERE a.idcliente = b.idcliente AND a.idcliente = c.idcliente AND d.valorlimite > 0 GROUP BY b.idcliente", nativeQuery = true)
	List<Prestamistas> getPrestamistasSemContrato();

}