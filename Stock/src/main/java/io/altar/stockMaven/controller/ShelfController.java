package io.altar.stockMaven.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	public void removeProductFromShelf(@PathParam("id") long id) {
		service.removeProductFromShelf(id);
	}

	// OK
	@PUT
	@Path("/addProductToShelf/{idShelf}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProductToShelf(@PathParam("idShelf") long idShelf, @QueryParam("idProduct") long idProduct) {
		service.addProductToShelf(idShelf, idProduct);
	}

	// OK
	@PUT
	@Path("/removeProductFromShelves/{productId}")
	public void remProductFromShelves(@PathParam("productId") long productId) {
		service.remProductFromShelves(productId);
	}

	@Override
	public String validateEntity(Shelf s) {
		return "" ;
	}

}
