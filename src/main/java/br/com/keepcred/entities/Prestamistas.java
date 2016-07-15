package br.com.keepcred.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "prestamista")
public class Prestamistas implements Serializable {

	private static final long serialVersionUID = -4986961620925186628L;

	@Id
	@Column
	private String numcpfcnpj;

	@Column
	private Date datanascimento;

	@Column
	private String descnomecliente;

	@Column
	private Long valorlimite;

	@Column
	private Short codtiposituacaotitulo;

	@Column
	private BigDecimal capitalsegurado;

	@Transient
	private Integer numcontas;

	// @Transient
	// private Integer operacao;

	public Prestamistas() {
		super();
	}

	public Prestamistas(String numcpfcnpj, Date datanascimento, String descnomecliente, Long valorlimite,
			Short codtiposituacaotitulo, BigDecimal capitalsegurado, Integer numcontas) {
		super();
		this.numcpfcnpj = numcpfcnpj;
		this.datanascimento = datanascimento;
		this.descnomecliente = descnomecliente;
		this.valorlimite = valorlimite;
		this.codtiposituacaotitulo = codtiposituacaotitulo;
		this.capitalsegurado = capitalsegurado;
		this.numcontas = numcontas;
	}

	public String getNumcpfcnpj() {
		return numcpfcnpj;
	}

	public void setNumcpfcnpj(String numcpfcnpj) {
		this.numcpfcnpj = numcpfcnpj;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getDescnomecliente() {
		return descnomecliente;
	}

	public void setDescnomecliente(String descnomecliente) {
		this.descnomecliente = descnomecliente;
	}

	public Long getValorlimite() {
		return valorlimite;
	}

	public void setValorlimite(Long valorlimite) {
		this.valorlimite = valorlimite;
	}

	public Short getCodtiposituacaotitulo() {
		return codtiposituacaotitulo;
	}

	public void setCodtiposituacaotitulo(Short codtiposituacaotitulo) {
		this.codtiposituacaotitulo = codtiposituacaotitulo;
	}

	public BigDecimal getCapitalsegurado() {
		return capitalsegurado;
	}

	public void setCapitalsegurado(BigDecimal capitalsegurado) {
		this.capitalsegurado = capitalsegurado;
	}

	public Integer getNumcontas() {
		return numcontas;
	}

	public void setNumcontas(Integer numcontas) {
		this.numcontas = numcontas;
	}

	@Override
	public String toString() {
		return "Prestamistas [numcpfcnpj=" + numcpfcnpj + ", datanascimento=" + datanascimento + ", descnomecliente="
				+ descnomecliente + ", valorlimite=" + valorlimite + ", codtiposituacaotitulo=" + codtiposituacaotitulo
				+ ", capitalsegurado=" + capitalsegurado + ", numcontas=" + numcontas + "]";
	}

}