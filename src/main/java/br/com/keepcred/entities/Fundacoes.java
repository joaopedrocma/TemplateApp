package br.com.keepcred.entities;

public class Fundacoes {

	private Long codcliente;

	private String nome;

	private Long matricula;

	private Long valrubrica;

	public Fundacoes() {
		super();
	}

	public Fundacoes(Long codcliente, String nome, Long matricula, Long valrubrica) {
		super();
		this.codcliente = codcliente;
		this.nome = nome;
		this.matricula = matricula;
		this.valrubrica = valrubrica;
	}

	public Long getCodcliente() {
		return codcliente;
	}

	public void setCodcliente(Long codcliente) {
		this.codcliente = codcliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Long getValrubrica() {
		return valrubrica;
	}

	public void setValrubrica(Long valrubrica) {
		this.valrubrica = valrubrica;
	}

	@Override
	public String toString() {
		return "Fundacoes [codcliente=" + codcliente + ", nome=" + nome + ", matricula=" + matricula + ", valrubrica="
				+ valrubrica + "]";
	}

}
