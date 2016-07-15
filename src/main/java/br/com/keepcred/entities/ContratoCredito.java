package br.com.keepcred.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cre_contratocredito")
public class ContratoCredito implements Serializable {

	private static final long serialVersionUID = 3508536601471720386L;

	@Column
	private Long idcliente;

	@Column
	private Short codtiposituacaotitulo;

	@Id
	@Column
	private Long numcontratocredito;

	@Column
	private Short idmodalidadeproduto;

	@Column
	private BigDecimal valorsaldodevedorcontabil;

	public ContratoCredito() {
		super();
	}

	public ContratoCredito(Long idcliente, Short codtiposituacaotitulo, Long numcontratocredito,
			Short idmodalidadeproduto, BigDecimal valorsaldodevedorcontabil) {
		super();
		this.idcliente = idcliente;
		this.codtiposituacaotitulo = codtiposituacaotitulo;
		this.numcontratocredito = numcontratocredito;
		this.idmodalidadeproduto = idmodalidadeproduto;
		this.valorsaldodevedorcontabil = valorsaldodevedorcontabil;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Short getCodtiposituacaotitulo() {
		return codtiposituacaotitulo;
	}

	public void setCodtiposituacaotitulo(Short codtiposituacaotitulo) {
		this.codtiposituacaotitulo = codtiposituacaotitulo;
	}

	public Long getNumcontratocredito() {
		return numcontratocredito;
	}

	public void setNumcontratocredito(Long numcontratocredito) {
		this.numcontratocredito = numcontratocredito;
	}

	public Short getIdmodalidadeproduto() {
		return idmodalidadeproduto;
	}

	public void setIdmodalidadeproduto(Short idmodalidadeproduto) {
		this.idmodalidadeproduto = idmodalidadeproduto;
	}

	public BigDecimal getValorsaldodevedorcontabil() {
		return valorsaldodevedorcontabil;
	}

	public void setValorsaldodevedorcontabil(BigDecimal valorsaldodevedorcontabil) {
		this.valorsaldodevedorcontabil = valorsaldodevedorcontabil;
	}

	@Override
	public String toString() {
		return "ContratoCredito [idcliente=" + idcliente + ", codtiposituacaotitulo=" + codtiposituacaotitulo
				+ ", numcontratocredito=" + numcontratocredito + ", idmodalidadeproduto=" + idmodalidadeproduto
				+ ", valorsaldodevedorcontabil=" + valorsaldodevedorcontabil + "]";
	}

}
