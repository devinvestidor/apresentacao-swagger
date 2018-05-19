package br.com.karanalpe.swagger.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class Aluno {

	final String URL_BASE = "http://localhost:8080/apresentacao-swagger/rest/aluno/";

	private Long id;
	private String nome;

	@ApiModelProperty(hidden = true)
	private List<Link> links;

	public Aluno() {
		this(-1l, "");
	}

	public Aluno(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;

		links = new ArrayList<Link>();
		links.add(new Link("consulta", URL_BASE + getId()));
	}

	public Long getId() {
		return id;
	}

	public List<Link> getLinks() {
		return links;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
		links = new ArrayList<Link>();
		links.add(new Link("consulta", URL_BASE + getId()));
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
