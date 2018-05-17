package br.com.karanalpe.swagger.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario")
public class ServiceUsuario {

	static List<String> pessoas = new ArrayList<String>();

	@GET
	@Path("/nome/{nome}")
	public Response buscarPorNome(@PathParam("nome") String nome) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nome)) {
				return Response.status(200).entity(pessoas.get(i)).build();
			}
		}
		return Response.status(200).entity("Pessoa não encontrada").build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	@Path("/buscar")
	public Response buscarPorParametro(@QueryParam("nome") String nome) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nome)) {
				return Response.status(200).entity(pessoas.get(i)).build();
			}
		}
		return Response.status(200).entity("Pessoa não encontrada").build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response buscarTodos() {
		return Response.status(200).entity(pessoas).build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response editar(String objeto) {

		String array[] = objeto.split(";");
		String nomeAntigo = array[0];
		String nomeNovo = array[1];

		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nomeAntigo)) {
				pessoas.set(i, nomeNovo);
			}
		}

		return Response.status(200).entity(pessoas).build();
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response excluir(String nome) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nome)) {
				pessoas.remove(i);
			}
		}

		return Response.status(200).entity(pessoas).build();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response inserir(String nome) {
		pessoas.add(nome);
		return Response.status(200).entity(pessoas).build();
	}

}
