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

@Path("/crude")
public class ServiceCrude {

	static List<String> pessoas = new ArrayList<String>();

	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response delete(String nome) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nome)) {
				pessoas.remove(i);
			}
		}

		return Response.status(200).entity(pessoas).build();
	}

	@PUT
	@Path("/edit")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response edit(String objeto) {

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

	@POST
	@Path("/insert")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response insert(String nome) {
		pessoas.add(nome);
		return Response.status(200).entity(pessoas).build();
	}

	@GET
	@Path("/select")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public Response select() {
		return Response.status(200).entity(pessoas).build();
	}

	@GET
	@Path("/select_nome_path/{nome}")
	public Response selectNomePath(@PathParam("nome") String nome) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nome)) {
				return Response.status(200).entity(pessoas.get(i)).build();
			}
		}
		return Response.status(200).entity("Pessoa não encontrada").build();
	}

	@GET
	@Path("/select_nome_query")
	public Response selectNomeQuery(@QueryParam("nome") String nome) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).equals(nome)) {
				return Response.status(200).entity(pessoas.get(i)).build();
			}
		}
		return Response.status(200).entity("Pessoa não encontrada").build();
	}

}
