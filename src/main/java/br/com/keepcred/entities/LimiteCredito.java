package br.com.keepcred.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cco_limitecredito")
public class LimiteCredito implements Serializable {

	private static final long serialVersionUID = 6414311092002536143L;

	@Id
	@Column
	private Long numcontacorrente;

	@Column
	private Long valorlimite;

	public LimiteCredito() {
		super();
	}

	public LimiteCredito(Long numcontacorrente, Long valorlimite) {
		super();
		this.numcontacorrente = numcontacorrente;
		this.valorlimite = valorlimite;
	}

	public Long getNumcontacorrente() {
		return numcontacorrente;
	}

	public void setNumcontacorrente(Long numcontacorrente) {
		this.numcontacorrente = numcontacorrente;
	}

	public Long getValorlimite() {
		return valorlimite;
	}

	public void setValorlimite(Long valorlimite) {
		this.valorlimite = valorlimite;
	}

	@Override
	public String toString() {
		return "LimiteCredito [numcontacorrente=" + numcontacorrente + ", valorlimite=" + valorlimite + "]";
	}

}
