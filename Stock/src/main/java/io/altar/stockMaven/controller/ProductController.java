
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
	public void removeProductFromShelves(List<Long> shelvesIdsList) {
		service.removeProductFromShelves(shelvesIdsList);
	}

	// OK
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/removeFromAllShelves/{id}")
	public void removeFromAllShelves(@PathParam("id") long id) {
		service.removeFromAllShelves(id);
	}

	// OK
	@PUT
	@Path("/addProductToShelves/{idProduct}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProductToShelves(@PathParam("idProduct") long idProduct, List<Long> shelvesIdsList) {
		service.addProductToShelves(idProduct, shelvesIdsList);
	}

	@GET
	@Path("/getProductsByDiscount/{pDiscount}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsByDiscount(@PathParam("pDiscount") float pDiscount) {
		return service.getProductsByDiscount(pDiscount);
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
