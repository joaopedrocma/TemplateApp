package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cli_clienteinstunidade")
public class ClienteInstunidade {

	@Id
	@Column
	private Long IDCLIENTE;

	@Column
	private String DESCNOMECLIENTE;

	@Column
	private String NUMCPFCNPJ;

	public ClienteInstunidade() {
		super();
	}

	public ClienteInstunidade(Long iDCLIENTE, String dESCNOMECLIENTE, String nUMCPFCNPJ) {
		super();
		IDCLIENTE = iDCLIENTE;
		DESCNOMECLIENTE = dESCNOMECLIENTE;
		NUMCPFCNPJ = nUMCPFCNPJ;
	}

	public Long getIDCLIENTE() {
		return IDCLIENTE;
	}

	public void setIDCLIENTE(Long iDCLIENTE) {
		IDCLIENTE = iDCLIENTE;
	}

	public String getDESCNOMECLIENTE() {
		return DESCNOMECLIENTE;
	}

	public void setDESCNOMECLIENTE(String dESCNOMECLIENTE) {
		DESCNOMECLIENTE = dESCNOMECLIENTE;
	}

	public String getNUMCPFCNPJ() {
		return NUMCPFCNPJ;
	}

	public void setNUMCPFCNPJ(String nUMCPFCNPJ) {
		NUMCPFCNPJ = nUMCPFCNPJ;
	}

	@Override
	public String toString() {
		return "ClienteInstunidade [IDCLIENTE=" + IDCLIENTE + ", DESCNOMECLIENTE=" + DESCNOMECLIENTE + ", NUMCPFCNPJ="
				+ NUMCPFCNPJ + "]";
	}
}
