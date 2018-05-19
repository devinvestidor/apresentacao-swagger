package br.com.karanalpe.swagger.model;

public class Link {

	private String operacao;
	private String href;

	public Link() {
		this("", "");
	}

	public Link(String operacao, String href) {
		super();
		this.operacao = operacao;
		this.href = href;
	}

	public String getHref() {
		return href;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	@Override
	public String toString() {
		return "Link [operacao=" + operacao + ", href=" + href + "]";
	}

}
