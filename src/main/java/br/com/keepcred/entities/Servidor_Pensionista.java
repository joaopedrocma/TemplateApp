package br.com.keepcred.entities;

public class Servidor_Pensionista {

	// private Long codorgao;

	private String instituicao;

	private Long matricula;

	// private Long codupag;

	// private String ufupag;

	private String nome;

	private String cpf;

	private Long codrubrica;

	// private Long seqrubrica;

	private Long valrubricaretorno;

	private Long valrubricaenvio;

	public Servidor_Pensionista() {
		super();
	}

	public Servidor_Pensionista(String instituicao, Long matricula, String nome, String cpf, Long codrubrica,
			Long valrubricaretorno, Long valrubricaenvio) {
		super();
		this.instituicao = instituicao;
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.codrubrica = codrubrica;
		this.valrubricaretorno = valrubricaretorno;
		this.valrubricaenvio = valrubricaenvio;
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

	public Long getCodrubrica() {
		return codrubrica;
	}

	public void setCodrubrica(Long codrubrica) {
		this.codrubrica = codrubrica;
	}

	public Long getValrubricaretorno() {
		return valrubricaretorno;
	}

	public void setValrubricaretorno(Long valrubricaretorno) {
		this.valrubricaretorno = valrubricaretorno;
	}

	public Long getValrubricaenvio() {
		return valrubricaenvio;
	}

	public void setValrubricaenvio(Long valrubricaenvio) {
		this.valrubricaenvio = valrubricaenvio;
	}
}
