package br.com.keepcred.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Prestamista {

	// @Column
	// private Long IDCLIENTE;

	@Column
	private Date DATANASCIMENTO;

	@Column
	private String DESCNOMECLIENTE;

	@Id
	@Column
	private String NUMCPFCNPJ;

	@Transient
	private Integer OPERACAO;

	@Column
	private Integer NUMCONTAS;

	// @Column
	// private Long NUMCONTACORRENTE;

	@Column
	private Long VALORLIMITE;

	// @Column
	// private Long NUMCONTRATOCREDITO;

	// @Column
	// private Long IDMODALIDADEPRODUTO;

	@Column
	private Integer CODTIPOSITUACAOTITULO;

	@Column
	private Double CAPITALSEGURADO;

	public Prestamista() {
		super();
	}

	public Prestamista(Date dATANASCIMENTO, String dESCNOMECLIENTE, String nUMCPFCNPJ, Integer oPERACAO,
			Integer nUMCONTAS, Long vALORLIMITE, Integer cODTIPOSITUACAOTITULO, Double vALORSALDODEVEDOR,
			Double cAPITALSEGURADO) {
		super();
		DATANASCIMENTO = dATANASCIMENTO;
		DESCNOMECLIENTE = dESCNOMECLIENTE;
		NUMCPFCNPJ = nUMCPFCNPJ;
		OPERACAO = oPERACAO;
		NUMCONTAS = nUMCONTAS;
		VALORLIMITE = vALORLIMITE;
		CODTIPOSITUACAOTITULO = cODTIPOSITUACAOTITULO;
		CAPITALSEGURADO = cAPITALSEGURADO;
	}

	public Date getDATANASCIMENTO() {
		return DATANASCIMENTO;
	}

	public void setDATANASCIMENTO(Date dATANASCIMENTO) {
		DATANASCIMENTO = dATANASCIMENTO;
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

	public Integer getOPERACAO() {
		return OPERACAO;
	}

	public void setOPERACAO(Integer oPERACAO) {
		OPERACAO = oPERACAO;
	}

	public Integer getNUMCONTAS() {
		return NUMCONTAS;
	}

	public void setNUMCONTAS(Integer nUMCONTAS) {
		NUMCONTAS = nUMCONTAS;
	}

	public Long getVALORLIMITE() {
		return VALORLIMITE;
	}

	public void setVALORLIMITE(Long vALORLIMITE) {
		VALORLIMITE = vALORLIMITE;
	}

	public Integer getCODTIPOSITUACAOTITULO() {
		return CODTIPOSITUACAOTITULO;
	}

	public void setCODTIPOSITUACAOTITULO(Integer cODTIPOSITUACAOTITULO) {
		CODTIPOSITUACAOTITULO = cODTIPOSITUACAOTITULO;
	}

	public Double getCAPITALSEGURADO() {
		return CAPITALSEGURADO;
	}

	public void setCAPITALSEGURADO(Double cAPITALSEGURADO) {
		CAPITALSEGURADO = cAPITALSEGURADO;
	}

	@Override
	public String toString() {
		return "Prestamista [DATANASCIMENTO=" + DATANASCIMENTO + ", DESCNOMECLIENTE=" + DESCNOMECLIENTE
				+ ", NUMCPFCNPJ=" + NUMCPFCNPJ + ", OPERACAO=" + OPERACAO + ", NUMCONTAS=" + NUMCONTAS
				+ ", VALORLIMITE=" + VALORLIMITE + ", CODTIPOSITUACAOTITULO=" + CODTIPOSITUACAOTITULO
				+ ", CAPITALSEGURADO=" + CAPITALSEGURADO + "]";
	}

}