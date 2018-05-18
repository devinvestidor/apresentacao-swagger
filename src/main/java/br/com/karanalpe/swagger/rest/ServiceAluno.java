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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Buscar aluno por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Aluno.class),
			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Response buscarPorId(@PathParam("id") Long id) {
		Aluno aluno = alunos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);

		return aluno != null ? Response.status(200).entity(aluno).build() : Response.status(204).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Buscar todos alunos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Response buscarTodos() {
		return alunos.size() != 0 ? Response.status(200).entity(alunos).build() : Response.status(204).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Editar aluno por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Response editar(@PathParam("id") Long id, Aluno aluno) {
		Response response = Response.status(204).entity("Aluno não encontrado").build();
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Excluir aluno por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Response excluir(@PathParam("id") Long id) {
		Response response = Response.status(204).entity("Aluno não encontrado").build();
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
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Inserir aluno")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 500, message = "Erro interno no servidor") })
	public Response inserir(Aluno aluno) {
		aluno.setId(getProximoId());
		alunos.add(aluno);

		return Response.status(200).entity(alunos).build();
	}

}
