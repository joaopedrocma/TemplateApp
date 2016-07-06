package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recusado {

	@Id
	@Column
	private Long IDCLIENTE;

	@Column
	private String DESCNOMECLIENTE;

	@Column
	private String NUMCPFCNPJ;

	@Column
	private String SITUACAO;

	public Recusado() {
		super();
	}

	public Recusado(Long iDCLIENTE, String dESCNOMECLIENTE, String nUMCPFCNPJ, String sITUACAO) {
		super();
		IDCLIENTE = iDCLIENTE;
		DESCNOMECLIENTE = dESCNOMECLIENTE;
		NUMCPFCNPJ = nUMCPFCNPJ;
		SITUACAO = sITUACAO;
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

	public String getSITUACAO() {
		return SITUACAO;
	}

	public void setSITUACAO(String sITUACAO) {
		SITUACAO = sITUACAO;
	}

	@Override
	public String toString() {
		return "Recusado [IDCLIENTE=" + IDCLIENTE + ", DESCNOMECLIENTE=" + DESCNOMECLIENTE + ", NUMCPFCNPJ="
				+ NUMCPFCNPJ + ", SITUACAO=" + SITUACAO + "]";
	}

}
