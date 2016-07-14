package br.com.keepcred.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cre_contratocredito")
public class ContratoCredito {

	@Column
	private Long idcliente;

	@Column
	private Long codtiposituacaotitulo;

	@Id
	@Column
	private Long numcontratocredito;

	@Column
	private Long idmodalidadeproduto;

	@Column
	private BigDecimal valorsaldodevedorcontabil;

	public ContratoCredito() {
		super();
	}

	public ContratoCredito(Long idcliente, Long codtiposituacaotitulo, Long numcontratocredito,
			Long idmodalidadeproduto, BigDecimal valorsaldodevedorcontabil) {
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

	public Long getCodtiposituacaotitulo() {
		return codtiposituacaotitulo;
	}

	public void setCodtiposituacaotitulo(Long codtiposituacaotitulo) {
		this.codtiposituacaotitulo = codtiposituacaotitulo;
	}

	public Long getNumcontratocredito() {
		return numcontratocredito;
	}

	public void setNumcontratocredito(Long numcontratocredito) {
		this.numcontratocredito = numcontratocredito;
	}

	public Long getIdmodalidadeproduto() {
		return idmodalidadeproduto;
	}

	public void setIdmodalidadeproduto(Long idmodalidadeproduto) {
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
