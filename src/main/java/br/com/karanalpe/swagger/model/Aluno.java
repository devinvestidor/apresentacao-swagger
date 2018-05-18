package br.com.karanalpe.swagger.model;

public class Aluno {

	private Long id;
	private String nome;

	public Aluno() {
		this(-1l, "");
	}

	public Aluno(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + "]";
	}

}
