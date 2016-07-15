package br.com.keepcred.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cli_clientefisica")
public class ClienteFisica implements Serializable {

	private static final long serialVersionUID = 427776964113945743L;

	@Id
	@Column
	private Long idcliente;

	@Column
	private Date datanascimento;

	public ClienteFisica() {
		super();
	}

	public ClienteFisica(Long idcliente, Date datanascimento) {
		super();
		this.idcliente = idcliente;
		this.datanascimento = datanascimento;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	@Override
	public String toString() {
		return "ClienteFisica [idcliente=" + idcliente + ", datanascimento=" + datanascimento + "]";
	}

}
