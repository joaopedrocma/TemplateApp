package br.com.keepcred.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.keepcred.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query(value = "select a.IDCLIENTE, a.DESCNOMECLIENTE, a.NUMCPFCNPJ, b.NUMCONTACORRENTE "
			+ "from spu_cli_clienteinstunidade a, spu_cco_participantecontacor b "
			+ "where a.IDCLIENTE = b.IDCLIENTE "
			+ "order by DESCNOMECLIENTE ", nativeQuery = true)
	List<Cliente> getAllData();
}
