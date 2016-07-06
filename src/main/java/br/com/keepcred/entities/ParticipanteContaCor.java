package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spu_cco_participantecontacor")
public class ParticipanteContaCor {
	
	@Id
	@Column
	private Long IDCLIENTE;
	
	@Column
	private Long NUMCONTACORRENTE;

	public ParticipanteContaCor() {
		super();
	}

	public ParticipanteContaCor(Long iDCLIENTE, Long nUMCONTACORRENTE) {
		super();
		IDCLIENTE = iDCLIENTE;
		NUMCONTACORRENTE = nUMCONTACORRENTE;
	}

	public Long getIDCLIENTE() {
		return IDCLIENTE;
	}

	public void setIDCLIENTE(Long iDCLIENTE) {
		IDCLIENTE = iDCLIENTE;
	}

	public Long getNUMCONTACORRENTE() {
		return NUMCONTACORRENTE;
	}

	public void setNUMCONTACORRENTE(Long nUMCONTACORRENTE) {
		NUMCONTACORRENTE = nUMCONTACORRENTE;
	}

	@Override
	public String toString() {
		return "ParticipanteContaCor [IDCLIENTE=" + IDCLIENTE + ", NUMCONTACORRENTE=" + NUMCONTACORRENTE + "]";
	}

}
