package br.com.keepcred.entities;

public class Servidor {

	// private Long codorgao;

	private Long matricula;

	// private Long codupag;

	// private String ufupag;

	private String nome;

	private String cpf;

	private Long codrubrica;

	// private Long seqrubrica;

	private Long valrubrica;

	public Servidor() {
		super();
	}

	public Servidor(Long matricula, String nome, String cpf, Long codrubrica, Long valrubrica) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.codrubrica = codrubrica;
		this.valrubrica = valrubrica;
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

	public Long getCodrubrica() {
		return codrubrica;
	}

	public void setCodrubrica(Long codrubrica) {
		this.codrubrica = codrubrica;
	}

	public Long getValrubrica() {
		return valrubrica;
	}

	public void setValrubrica(Long valrubrica) {
		this.valrubrica = valrubrica;
	}

	@Override
	public String toString() {
		return "Servidor [matricula=" + matricula + ", nome=" + nome + ", cpf=" + cpf + ", codrubrica=" + codrubrica
				+ ", valrubrica=" + valrubrica + "]";
	}

}
