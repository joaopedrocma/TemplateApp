package br.com.keepcred.entities;

public class ClienteCapital {

	// private Long codcliente;

	private String instituicao;
	
	private Long matricula;

	private String nome;

	private String cpf;

	private Long valrubrica;

	public ClienteCapital() {
		super();
	}

	public ClienteCapital(String instituicao, Long matricula, String nome, String cpf, Long valrubrica) {
		super();
		this.instituicao = instituicao;
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.valrubrica = valrubrica;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getValrubrica() {
		return valrubrica;
	}

	public void setValrubrica(Long valrubrica) {
		this.valrubrica = valrubrica;
	}

	@Override
	public String toString() {
		return "ClienteCapital [instituicao=" + instituicao + ", matricula=" + matricula + ", nome=" + nome + ", cpf="
				+ cpf + ", valrubrica=" + valrubrica + "]";
	}

	
	
}
