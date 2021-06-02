package io.altar.stockMaven.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.stockMaven.models.Shelf;
import io.altar.stockMaven.repositories.ShelfRepository;
import io.altar.stockMaven.service.ShelfService;

@RequestScoped
@Path("shelves")

public class ShelfController extends EntityController<ShelfService, ShelfRepository, Shelf> {

	// OK
	@GET
	@Path("/howManyShelves")
	@Produces(MediaType.TEXT_PLAIN)
	public long HowManyShelves() {
		return service.howManyShelves();
	}

	// OK
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shelf> getAllShelves() {
		return service.getAllShelves();
	}

	// OK
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmptyShelves")
	public List<Shelf> getEmptyShelves() {
		return service.getEmptyShelves();
	}

	// OK
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getShelvesByProductId/{id}")
	public List<Shelf> getShelvesWithProduct(@PathParam("id") long id) {
		return service.getShelvesWithProduct(id);
	}

	// OK
	@PUT
	@Path("/removeProductFromShelf/{id}")
	public Response removeProductFromShelf(@PathParam("id") long id) {
		service.removeProductFromShelf(id);
		return Response.ok().entity("Product Removed From Shelf "+id).build();
	}

	// OK
	@PUT
	@Path("/addProductToShelf/{idProduct}/{idShelf}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProductToShelf(@PathParam("idShelf") long idShelf, @PathParam("idProduct") long idProduct) {
		service.addProductToShelf(idShelf, idProduct);
		return Response.ok().entity("Product Added To All Shelves").build();
	}

	// OK
	@PUT
	@Path("/removeProductFromShelves/{productId}")
	public Response remProductFromShelves(@PathParam("productId") long productId) {
		service.remProductFromShelves(productId);
		return Response.ok().entity("Product Removed From All Shelves").build();
	}

	@GET
	@Path("/getShelvesOrderedByKeyAsc/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelvesOrderedByKeyAsc(@PathParam("key") String key) throws Exception {
		try {
			return Response.ok(service.getShelvesOrderedByKeyAsc(key)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path("/getShelvesOrderedByKeyDesc/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelvesOrderedByKeyDesc(@PathParam("key") String key) throws Exception {
		try {
			return Response.ok(service.getShelvesOrderedByKeyDesc(key)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/getShelvesByKeyValue/{key}/{keyValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelvesByKeyValue(@PathParam("keyValue") Float keyValue, @PathParam("key") String key)
			throws Exception {
		try {
			return Response.ok(service.getShelvesByKeyValue(keyValue, key)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@Override
	public String validateEntity(Shelf s) {
		return "";
	}

}
