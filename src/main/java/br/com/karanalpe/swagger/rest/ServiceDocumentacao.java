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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import io.swagger.jaxrs.Reader;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

@Path("/documentacao")
public class ServiceDocumentacao {

	private Response buildResponse(Status status, Object data, String type) {
		ResponseBuilder rb = Response.status(status);
		if (StringUtils.isNotEmpty(type)) {
			rb = rb.type(type);
		}
		rb = rb.entity(data);
		rb = rb.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").header("Access-Control-Max-Age", "1209600");
		return rb.build();
	}

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
	public Response getJsonSwagger() throws JsonProcessingException, UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Swagger swagger = new Reader(new Swagger()).read(getClasses());
		swagger.setBasePath("/apresentacao-swagger/rest");
		setInfo(swagger);

		return Response.status(200).entity(getJson(swagger)).build();

	}

	private String getJsonSwagger(Swagger swagger) throws JsonProcessingException {
		return Json.mapper().writeValueAsString(swagger);
	}

	public Response getResponse(Status status, Object data, String type) {
		return buildResponse(status, data, type);
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
