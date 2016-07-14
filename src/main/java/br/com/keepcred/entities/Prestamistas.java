package br.com.keepcred.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Prestamistas {

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
	private Integer codtiposituacaotitulo;

	@Column
	private BigDecimal capitalsegurado;

	@Transient
	private Integer numcontas;

	// @Transient
	// private Integer operacao;

	public Prestamistas() {
		super();
	}

	public Prestamistas(String numcpfcnpj, Date datanascimento, String descnomecliente, Integer numcontas,
			Long valorlimite, Integer codtiposituacaotitulo, BigDecimal capitalsegurado) {
		super();
		this.numcpfcnpj = numcpfcnpj;
		this.datanascimento = datanascimento;
		this.descnomecliente = descnomecliente;
		this.numcontas = numcontas;
		this.valorlimite = valorlimite;
		this.codtiposituacaotitulo = codtiposituacaotitulo;
		this.capitalsegurado = capitalsegurado;
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

	public Integer getNumcontas() {
		return numcontas;
	}

	public void setNumcontas(Integer numcontas) {
		this.numcontas = numcontas;
	}

	public Long getValorlimite() {
		return valorlimite;
	}

	public void setValorlimite(Long valorlimite) {
		this.valorlimite = valorlimite;
	}

	public Integer getCodtiposituacaotitulo() {
		return codtiposituacaotitulo;
	}

	public void setCodtiposituacaotitulo(Integer codtiposituacaotitulo) {
		this.codtiposituacaotitulo = codtiposituacaotitulo;
	}

	public BigDecimal getCapitalsegurado() {
		return capitalsegurado;
	}

	public void setCapitalsegurado(BigDecimal capitalsegurado) {
		this.capitalsegurado = capitalsegurado;
	}

	@Override
	public String toString() {
		return "Prestamistas [numcpfcnpj=" + numcpfcnpj + ", datanascimento=" + datanascimento + ", descnomecliente="
				+ descnomecliente + ", numcontas=" + numcontas + ", valorlimite=" + valorlimite
				+ ", codtiposituacaotitulo=" + codtiposituacaotitulo + ", capitalsegurado=" + capitalsegurado + "]";
	}

}