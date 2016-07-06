package br.com.keepcred.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.keepcred.entities.Recusado;

public interface RecusadoRepository extends JpaRepository<Recusado, Long> {

	@Query(value = "SELECT IDCLIENTE, DESCNOMECLIENTE, NUMCPFCNPJ, SITUACAO " + "from recusado order by DESCNOMECLIENTE", nativeQuery = true)
	List<Recusado> getRecusados();

}
