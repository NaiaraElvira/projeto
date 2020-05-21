package br.com.naiara.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import br.com.naiara.business.PessoaBusiness;
import br.com.naiara.entity.Pessoa;
import br.com.naiara.enumerator.TipoPessoaEnum;
import br.com.naiara.fiilter.JWTTokenNeeded;

@Path("pessoa")
@JWTTokenNeeded
public class PessoaResource {
	@Context
    private SecurityContext securityContext;
	
	@Inject
	private PessoaBusiness pessoaBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "ANALI", "GEREN"})
	public Response listarPessoas() {
		List<Pessoa> pessoas = pessoaBusiness.listar();
		return Response.ok(pessoas).build();
	}
	
	@POST
	@RolesAllowed({"ADMIN", "GEREN"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarPessoa(Pessoa pessoa) {
		String username = securityContext.getUserPrincipal().getName();
		pessoaBusiness.salvarPessoa(pessoa, username);
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({"ADMIN", "GEREN"})
	public Response alterarPessoa(Pessoa pessoa) {
		pessoaBusiness.alterarPessoa(pessoa);
		return Response.status(201).build();
	}
	
	@DELETE
	@Path("/{id}")
	@RolesAllowed({"ADMIN", "GEREN"})
	public Response excluirPessoa(final @PathParam("id") Long id) {
		pessoaBusiness.excluirPessoa(id);
		return Response.status(201).build();
	}
	
	@GET
	@Path("/{id}")
	@RolesAllowed({"ADMIN", "ANALI", "GEREN"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPessoa(final @PathParam("id") Long id) {
		Pessoa pessoa = pessoaBusiness.consultarPessoa(id);
		return Response.ok(pessoa).build();
	}
	
	@GET
	@Path("/tipo-pessoa")
	@RolesAllowed({"ADMIN", "ANALI", "GEREN"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarTipoPessoa() {
		return Response.ok(TipoPessoaEnum.listarEnum()).build();
	}

}
