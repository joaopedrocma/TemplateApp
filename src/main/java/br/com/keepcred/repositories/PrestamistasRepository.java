package br.com.keepcred.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.keepcred.entities.Prestamistas;

public interface PrestamistasRepository extends JpaRepository<Prestamistas, Long> {

	@Query(value = "select b.DESCNOMECLIENTE, b.NUMCPFCNPJ, a.DATANASCIMENTO, MIN(e.CODTIPOSITUACAOTITULO) as CODTIPOSITUACAOTITULO, "
			+ "count(distinct c.NUMCONTACORRENTE) as NUMCONTAS, MAX(coalesce(d.VALORLIMITE, 0)) as VALORLIMITE, "
			+ "sum(case when CODTIPOSITUACAOTITULO = 1 then e.VALORSALDODEVEDORCONTABIL else 0 end) as CAPITALSEGURADO "
			+ "from spu_cli_clientefisica a, spu_cli_clienteinstunidade b, spu_cco_participantecontacor c LEFT JOIN spu_cco_limitecredito d ON d.NUMCONTACORRENTE = c.NUMCONTACORRENTE, "
			+ "spu_cre_contratocredito e where a.IDCLIENTE = b.IDCLIENTE and a.IDCLIENTE = c.IDCLIENTE and a.IDCLIENTE = e.IDCLIENTE "
			+ "group by a.IDCLIENTE order by b.DESCNOMECLIENTE", nativeQuery = true)
	List<Prestamistas> getPrestamistasComContrato();

	@Query(value = "SELECT b.DESCNOMECLIENTE, b.NUMCPFCNPJ, a.DATANASCIMENTO, d.VALORLIMITE, 0.0 as CAPITALSEGURADO, 1 as NUMCONTAS, 2 as CODTIPOSITUACAOTITULO "
			+ "FROM spu_cli_clientefisica a, spu_cli_clienteinstunidade b, spu_cco_participantecontacor c LEFT JOIN spu_cco_limitecredito d ON d.NUMCONTACORRENTE = c.NUMCONTACORRENTE "
			+ "where a.IDCLIENTE = b.IDCLIENTE and a.IDCLIENTE = c.IDCLIENTE and d.VALORLIMITE > 0 group by b.IDCLIENTE", nativeQuery = true)
	List<Prestamistas> getPrestamistasSemContrato();

}