package br.com.desafio.rests;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.desafio.models.Role;
import br.com.desafio.services.RoleService;

@Path("/roles")
public class RoleRest {

	@Inject
	private RoleService service;
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Role> all = service.findAll();
		if(all.isEmpty()) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Role no exist").build();
		}
		return Response.status(Status.OK)
				.entity(new GenericEntity<List<Role>>(all) {}).build();
	}
}
