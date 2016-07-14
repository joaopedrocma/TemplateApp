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
	private Long idcliente;

	@Column
	private String descnomecliente;

	@Column
	private String numcpfcnpj;

	public ClienteInstunidade() {
		super();
	}

	public ClienteInstunidade(Long idcliente, String descnomecliente, String numcpfcnpj) {
		super();
		this.idcliente = idcliente;
		this.descnomecliente = descnomecliente;
		this.numcpfcnpj = numcpfcnpj;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public String getDescnomecliente() {
		return descnomecliente;
	}

	public void setDescnomecliente(String descnomecliente) {
		this.descnomecliente = descnomecliente;
	}

	public String getNumcpfcnpj() {
		return numcpfcnpj;
	}

	public void setNumcpfcnpj(String numcpfcnpj) {
		this.numcpfcnpj = numcpfcnpj;
	}

	@Override
	public String toString() {
		return "ClienteInstunidade [idcliente=" + idcliente + ", descnomecliente=" + descnomecliente + ", numcpfcnpj="
				+ numcpfcnpj + "]";
	}
}
