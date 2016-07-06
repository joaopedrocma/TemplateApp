package br.com.keepcred.entities;

public class ClienteEmprestimo {

	// private String identregistro;

	private String instituicao;

	private Long identclientecoop;

	private String nome;

	private String cpfcgc;

	// private Long modoperacao;

	private Long valrubrica;

	public ClienteEmprestimo() {
		super();
	}

	public ClienteEmprestimo(String instituicao, Long identclientecoop, String nome, String cpfcgc, Long valrubrica) {
		super();
		this.instituicao = instituicao;
		this.identclientecoop = identclientecoop;
		this.nome = nome;
		this.cpfcgc = cpfcgc;
		this.valrubrica = valrubrica;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Long getIdentclientecoop() {
		return identclientecoop;
	}

	public void setIdentclientecoop(Long identclientecoop) {
		this.identclientecoop = identclientecoop;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfcgc() {
		return cpfcgc;
	}

	public void setCpfcgc(String cpfcgc) {
		this.cpfcgc = cpfcgc;
	}

	public Long getValrubrica() {
		return valrubrica;
	}

	public void setValrubrica(Long valrubrica) {
		this.valrubrica = valrubrica;
	}

	@Override
	public String toString() {
		return "ClienteEmprestimo [instituicao=" + instituicao + ", identclientecoop=" + identclientecoop + ", nome="
				+ nome + ", cpfcgc=" + cpfcgc + ", valrubrica=" + valrubrica + "]";
	}

	

}
