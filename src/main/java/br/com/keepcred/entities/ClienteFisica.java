package br.com.keepcred.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cli_clientefisica")
public class ClienteFisica {

	@Id
	@Column
	private Long IDCLIENTE;
	
	@Column
	private Date DATANASCIMENTO;

	public ClienteFisica() {
		super();
	}

	public ClienteFisica(Long iDCLIENTE, Date dATANASCIMENTO) {
		super();
		IDCLIENTE = iDCLIENTE;
		DATANASCIMENTO = dATANASCIMENTO;
	}

	public Long getIDCLIENTE() {
		return IDCLIENTE;
	}

	public void setIDCLIENTE(Long iDCLIENTE) {
		IDCLIENTE = iDCLIENTE;
	}

	public Date getDATANASCIMENTO() {
		return DATANASCIMENTO;
	}

	public void setDATANASCIMENTO(Date dATANASCIMENTO) {
		DATANASCIMENTO = dATANASCIMENTO;
	}

	@Override
	public String toString() {
		return "ClienteFisica [IDCLIENTE=" + IDCLIENTE + ", DATANASCIMENTO=" + DATANASCIMENTO + "]";
	}

}
