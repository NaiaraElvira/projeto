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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.naiara.business.OperadorBusiness;
import br.com.naiara.entity.Operador;
import br.com.naiara.enumerator.PerfilEnum;
import br.com.naiara.fiilter.JWTTokenNeeded;

@Path("operador")
@JWTTokenNeeded
public class OperadorResource {
	@Inject
	private OperadorBusiness operadorBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
	public Response listarOperador() {
		List<Operador> operadores = operadorBusiness.listar();
		return Response.ok(operadores).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
	public Response salvarOperador(Operador operador) {
		operadorBusiness.salvarOperador(operador);
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN"})
	public Response alterarOperador(Operador operador) {
		operadorBusiness.alterarOperador(operador);
		return Response.status(201).build();
	}
	
	@DELETE
	@Path("/{id}")	
    @RolesAllowed({"ADMIN"})
	public Response excluirOperador(final @PathParam("id") Long id) {
		operadorBusiness.excluirOperador(id);
		return Response.status(201).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)    
	@RolesAllowed({"ADMIN"})
	public Response consultarOperador(final @PathParam("id") Long id) {
		Operador operador = operadorBusiness.consultarOperador(id);
		return Response.ok(operador).build();
	}
	
	@GET
	@Path("/tipo-perfil")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarTiposTelefone() {
		return Response.ok(PerfilEnum.listarEnum()).build();
	}


}
