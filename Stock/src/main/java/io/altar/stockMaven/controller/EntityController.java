package io.altar.stockMaven.controller;

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

import io.altar.stockMaven.models.Entity2_;
import io.altar.stockMaven.repositories.EntityRepository;
import io.altar.stockMaven.service.EntityService;

public abstract class EntityController<S extends EntityService<R, E>, R extends EntityRepository<E>, E extends Entity2_> {

	@Inject
	protected S service;

	public abstract String validateEntity(E e);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEntity(E entity) {
		String error = validateEntity(entity);
		System.out.println(error);
		if (error.equals("")) {
			return Response.ok(service.addEntity(entity)).build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public E getEntity(@PathParam("id") long id) {
		return service.getEntity(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editEntity(E entity) {
		String error = validateEntity(entity);
		System.out.println(error);
		if (error.equals("")) {
			return Response.ok(service.editEntity(entity)).entity("Id " + entity.getId() + " edited").build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response removeEntity(@PathParam("id") long id) {
		System.out.println(getEntity(id));
		if (getEntity(id) != null) {
			service.removeEntity(id);
			return Response.ok().entity("Id " + id + " removed").build();
		} else {
			return Response.notModified().entity("That id is not in the database").build();
		}
	}
}
