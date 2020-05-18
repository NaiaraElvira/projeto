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

import br.com.naiara.business.TelefoneBusiness;
import br.com.naiara.entity.Telefone;
import br.com.naiara.fiilter.JWTTokenNeeded;

@Path("telefone")
@JWTTokenNeeded
public class TelefoneResource {
	@Inject
	private TelefoneBusiness telefoneBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTelefone() {
		List<Telefone> telefones = telefoneBusiness.listar();
		return Response.ok(telefones).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarPessoa(Telefone telefone) {
		telefoneBusiness.salvarTelefone(telefone);
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterarPessoa(Telefone telefone) {
		telefoneBusiness.alterarTelefone(telefone);
		return Response.status(201).build();
	}
	
	@DELETE
	@Path("/{id}")	
	public Response excluirPessoa(final @PathParam("id") Long id) {
		telefoneBusiness.excluirTelefone(id);
		return Response.status(201).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPessoa(final @PathParam("id") Long id) {
		Telefone telefone = telefoneBusiness.consultarTelefone(id);
		return Response.ok(telefone).build();
	}

}
