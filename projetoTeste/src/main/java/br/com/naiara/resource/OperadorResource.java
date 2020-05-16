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

import br.com.naiara.business.OperadorBusiness;
import br.com.naiara.entity.Operador;

@Path("operador")
public class OperadorResource {
	
	@Inject
	private OperadorBusiness agendamentoEmailBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarOperador() {
		List<Operador> operadores = agendamentoEmailBusiness.listar();
		return Response.ok(operadores).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarOperador(Operador operador) {
		agendamentoEmailBusiness.salvarOperador(operador);
		return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterarOperador(Operador operador) {
		agendamentoEmailBusiness.alterarOperador(operador);
		return Response.status(201).build();
	}
	
	@DELETE
	@Path("/{id}")	
	public Response excluirOperador(final @PathParam("id") Long id) {
		agendamentoEmailBusiness.excluirOperador(id);
		return Response.status(201).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarOperador(final @PathParam("id") Long id) {
		Operador operador = agendamentoEmailBusiness.consultarOperador(id);
		return Response.ok(operador).build();
	}

}
