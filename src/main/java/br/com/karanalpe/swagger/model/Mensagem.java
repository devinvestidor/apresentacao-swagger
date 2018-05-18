package br.com.karanalpe.swagger.model;

public class Mensagem {

	private String descricao;

	public Mensagem() {
		this("");
	}

	public Mensagem(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Mensagem [descricao=" + descricao + "]";
	}

}
