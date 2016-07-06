package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cco_limitecredito")
public class LimiteCredito {

	@Id
	@Column
	private Long NUMCONTACORRENTE;

	@Column
	private Long VALORLIMITE;

	public LimiteCredito() {
		super();
	}

	public LimiteCredito(Long nUMCONTACORRENTE, Long vALORLIMITE) {
		super();
		NUMCONTACORRENTE = nUMCONTACORRENTE;
		VALORLIMITE = vALORLIMITE;
	}

	public Long getNUMCONTACORRENTE() {
		return NUMCONTACORRENTE;
	}

	public void setNUMCONTACORRENTE(Long nUMCONTACORRENTE) {
		NUMCONTACORRENTE = nUMCONTACORRENTE;
	}

	public Long getVALORLIMITE() {
		return VALORLIMITE;
	}

	public void setVALORLIMITE(Long vALORLIMITE) {
		VALORLIMITE = vALORLIMITE;
	}

	@Override
	public String toString() {
		return "LimiteCredito [NUMCONTACORRENTE=" + NUMCONTACORRENTE + ", VALORLIMITE=" + VALORLIMITE + "]";
	}

}
