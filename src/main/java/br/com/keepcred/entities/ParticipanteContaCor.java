package br.com.keepcred.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cco_participantecontacor")
public class ParticipanteContaCor implements Serializable {

	private static final long serialVersionUID = 2687726501378592706L;

	@Column
	private Long idcliente;

	@Id
	@Column
	private Long numcontacorrente;

	public ParticipanteContaCor() {
		super();
	}

	public ParticipanteContaCor(Long idcliente, Long numcontacorrente) {
		super();
		this.idcliente = idcliente;
		this.numcontacorrente = numcontacorrente;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Long getNumcontacorrente() {
		return numcontacorrente;
	}

	public void setNumcontacorrente(Long numcontacorrente) {
		this.numcontacorrente = numcontacorrente;
	}

}
