package br.com.karanalpe.swagger.rest;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import io.swagger.jaxrs.Reader;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

@Path("/documentacao")
public class ServiceDocumentacao {

	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(ServiceAluno.class);
		return resources;
	}

	private String getJson(Swagger swagger) throws JsonProcessingException {
		String json = new Gson().toJson(getJsonSwagger(swagger));
		json = json.replaceAll("\\\\", "");
		json = json.substring(1, json.length() - 1);

		return json;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
	public Response getJsonSwagger()
			throws JsonProcessingException, UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Swagger swagger = new Reader(new Swagger()).read(getClasses());
		swagger.setBasePath("/apresentacao-swagger/rest");
		setInfo(swagger);

		return Response.status(200).entity(getJson(swagger)).build();

	}

	private String getJsonSwagger(Swagger swagger) throws JsonProcessingException {
		return Json.mapper().writeValueAsString(swagger);
	}

	private void setInfo(Swagger swagger) {
		Info info = new Info();
		info.setTitle("Documentando uma API REST em Java com o Swagger");

		StringBuilder descricao = new StringBuilder();
		descricao.append("Utilize os serviços listados abaixo para consultar, inserir ou apagar informações ");

		info.setDescription(descricao.toString());

		swagger.setInfo(info);
	}
}
