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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.karanalpe.swagger.model.Aluno;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/aluno")
@Api(value = "Aluno")
public class ServiceAluno {

	static List<Aluno> alunos = new ArrayList<Aluno>();

	public ServiceAluno() {
		if (alunos.isEmpty()) {
			alunos.add(new Aluno(1L, "Rodrigo"));
			alunos.add(new Aluno(2L, "Karan"));
			alunos.add(new Aluno(3L, "Fernando"));
			alunos.add(new Aluno(4L, "Sandro"));
			alunos.add(new Aluno(5L, "Luiz"));
		}
	}

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Buscar por ID")
	public Response buscarPorId(@PathParam("id") Long id) {
		Aluno aluno = alunos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
		return aluno != null ? Response.status(200).entity(aluno.toString()).build() : Response.status(200).entity("Aluno não encontrado").build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	@ApiOperation(value = "Buscar todos")
	public Response buscarTodos() {
		return Response.status(200).entity(alunos).build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	@Path("/{id}")
	@ApiOperation(value = "Editar por ID")
	public Response editar(@PathParam("id") Long id, Aluno aluno) {
		Response response = Response.status(200).entity("Aluno não encontrado").build();
		Aluno alunoEditar = alunos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);

		if (alunoEditar != null) {
			alunos.remove(alunoEditar);
			alunoEditar.setNome(aluno.getNome());
			alunos.add(alunoEditar);
			response = Response.status(200).entity(alunos).build();
		}

		return response;
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	@Path("/{id}")
	@ApiOperation(value = "Excluir por ID")
	public Response excluir(@PathParam("id") Long id) {
		Response response = Response.status(200).entity("Aluno não encontrado").build();
		Aluno aluno = alunos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);

		if (aluno != null) {
			alunos.remove(aluno);
			response = Response.status(200).entity(alunos).build();
		}

		return response;
	}

	private long getProximoId() {
		return alunos.get(alunos.size() - 1).getId() + 1;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	@ApiOperation(value = "Inserir")
	public Response inserir(Aluno aluno) {
		aluno.setId(getProximoId());
		alunos.add(aluno);

		return Response.status(200).entity(alunos.toString()).build();
	}

}
