package br.com.keepcred.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Column
	private Long IDCLIENTE;

	@Column
	private String DESCNOMECLIENTE;

	@Column
	private String NUMCPFCNPJ;

	@Id
	@Column
	private Long NUMCONTACORRENTE;

	@Transient
	private String dtpagto;

	@Transient
	private String Stotal;

	@Transient
	private Double total;

	public Cliente() {
		super();
	}

	public Cliente(Long iDCLIENTE, String dESCNOMECLIENTE, String nUMCPFCNPJ, Long nUMCONTACORRENTE, String dtpagto,
			String stotal, Double total) {
		super();
		IDCLIENTE = iDCLIENTE;
		DESCNOMECLIENTE = dESCNOMECLIENTE;
		NUMCPFCNPJ = nUMCPFCNPJ;
		NUMCONTACORRENTE = nUMCONTACORRENTE;
		this.dtpagto = dtpagto;
		Stotal = stotal;
		this.total = total;
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

	public Long getNUMCONTACORRENTE() {
		return NUMCONTACORRENTE;
	}

	public void setNUMCONTACORRENTE(Long nUMCONTACORRENTE) {
		NUMCONTACORRENTE = nUMCONTACORRENTE;
	}

	public String getDtpagto() {
		return dtpagto;
	}

	public void setDtpagto(String dtpagto) {
		this.dtpagto = dtpagto;
	}

	public String getStotal() {
		return Stotal;
	}

	public void setStotal(String stotal) {
		Stotal = stotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cliente [IDCLIENTE=" + IDCLIENTE + ", DESCNOMECLIENTE=" + DESCNOMECLIENTE + ", NUMCPFCNPJ=" + NUMCPFCNPJ
				+ ", NUMCONTACORRENTE=" + NUMCONTACORRENTE + ", dtpagto=" + dtpagto + ", Stotal=" + Stotal + ", total="
				+ total + "]";
	}
}
