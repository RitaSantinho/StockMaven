
package io.altar.stockMaven.controller;

import java.util.Arrays;
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

import io.altar.stockMaven.models.Product;
import io.altar.stockMaven.models.Shelf;
import io.altar.stockMaven.repositories.ProductRepository;
import io.altar.stockMaven.service.ProductService;

@RequestScoped
@Path("products")

public class ProductController extends EntityController<ProductService, ProductRepository, Product> {

	// OK
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}

	// OK
	@GET
	@Path("/howManyProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public long howManyProducts() {
		return service.howManyProducts();
	}

	// OK
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/whereIsProduct/{id}")
	public List<Shelf> whereIsProduct(@PathParam("id") long id) {
		return service.whereIsProduct(id);
	}

	// OK
	@PUT
	@Path("/removeProductFromShelves")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeProductFromShelves(List<Long> shelvesIdsList) {
		service.removeProductFromShelves(shelvesIdsList);
		return Response.ok().entity("Product Removed From All Shelves").build();
	}

	// OK
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/removeFromAllShelves/{id}")
	public Response removeFromAllShelves(@PathParam("id") long id) {
		service.removeFromAllShelves(id);
		return Response.ok().entity("Product Removed From All Shelves").build();
	}

	// OK
	@PUT
	@Path("/addProductToShelves/{idProduct}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProductToShelves(@PathParam("idProduct") long idProduct, List<Long> shelvesIdsList) {
		
		service.addProductToShelves(idProduct, shelvesIdsList);
		return Response.ok().entity("Product Added To All Shelves").build();
	}

	@GET
	@Path("/getProductsOrderedByKeyAsc/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsOrderedByKeyAsc(@PathParam("key") String key) throws Exception {
		try {
			return Response.ok(service.getProductsOrderedByKeyAsc(key)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/getProductsOrderedByKeyDesc/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsOrderedByKeyDesc(@PathParam("key") String key) throws Exception {
		try {
			return Response.ok(service.getProductsOrderedByKeyDesc(key)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/getProductsByKeyValue/{key}/{keyValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsByKeyValue(@PathParam("keyValue") Float keyValue, @PathParam("key") String key)
			throws Exception{
		try {
			return Response.ok(service.getProductsByKeyValue(keyValue, key)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	public String validateEntity(Product p) {
		String result = "";
		Integer[] validIva = { 6, 13, 23 };
		if (!Arrays.asList(validIva).contains(p.getIva())) {
			result = result.concat("Valor de Iva inválido");
		}
		if (p.getDiscount() > p.getPvp()) {
			result = result.concat(" O desconto não pode ser superior ao PVP");
		}
		return result;
	}
}
