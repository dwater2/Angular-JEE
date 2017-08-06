package br.com.desafio.rests;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.desafio.models.User;
import br.com.desafio.services.UserService;

@Path("/users")
public class UserRest {

	@Inject
	private UserService service;

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<User> all = service.findAll();
		return Response.status(Status.OK).entity(new GenericEntity<List<User>>(all) {
		}).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		User found = service.find(id);
		if (found == null) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("User no exist").build();
		}
		return Response.status(Status.OK).entity(found).build();
	}

	@Path("/find")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(User user) {

		if ((user == null)) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Favor informar pelo menos um filtro!").build();
		}

		List<User> all = service.findAll(user);
		return Response.status(Status.OK).entity(new GenericEntity<List<User>>(all) {
		}).build();
	}

	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid User user) {
		User userExist = service.find(user.getCpf());
		if(userExist != null){
			return Response.status(Status.FORBIDDEN).entity("User exist").build();
		}
		service.create(user);
		return Response.status(Status.OK).entity(user).build();
	}

	@Path("/update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(User user) {
		System.out.println(user);
		service.update(user);
		return Response.status(Status.OK).entity(user).build();
	}

	@Path("/delete")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(User user) {
		service.remove(user);
		return Response.status(Status.OK).entity(user).build();
	}
}