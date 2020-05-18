package br.com.naiara.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.naiara.business.PessoaBusiness;
import br.com.naiara.entity.Pessoa;
import br.com.naiara.fiilter.JWTTokenNeeded;

@Path("pessoa")
@JWTTokenNeeded
public class PessoaResource {
	
	@Inject
	private PessoaBusiness pessoaBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPessoas() {
		List<Pessoa> pessoas = pessoaBusiness.listar();
		return Response.ok(pessoas).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarPessoa(Pessoa pessoa) {
		pessoaBusiness.salvarPessoa(pessoa);
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterarPessoa(Pessoa pessoa) {
		pessoaBusiness.alterarPessoa(pessoa);
		return Response.status(201).build();
	}
	
	@DELETE
	@Path("/{id}")	
	public Response excluirPessoa(final @PathParam("id") Long id) {
		pessoaBusiness.excluirPessoa(id);
		return Response.status(201).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPessoa(final @PathParam("id") Long id) {
		Pessoa pessoa = pessoaBusiness.consultarPessoa(id);
		return Response.ok(pessoa).build();
	}

}
