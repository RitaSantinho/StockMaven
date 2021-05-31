package io.altar.stockMaven.service;

import io.altar.stockMaven.models.Product;
import io.altar.stockMaven.models.Shelf;
import io.altar.stockMaven.repositories.ShelfRepository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ShelfService extends EntityService<ShelfRepository, Shelf> {

	@Inject
	ProductService productS;

	public long howManyShelves() {
		return repository.howManyShelves();
	}

	public List<Shelf> getAllShelves() {
		return repository.getAllShelves();
	}

	public List<Shelf> getEmptyShelves() {
		return repository.getEmptyShelves();
	}

	public List<Shelf> getShelvesWithProduct(long id) {
		return repository.getShelvesWithProduct(id);
	}

	public void removeProductFromShelf(long id) {
		Shelf shelf = repository.getEntity(id);
		shelf.setProduct(null);
		repository.editEntity(shelf);
	}

	public void addProductToShelf(long idShelf, long idProduct) {
		Product product = productS.getEntity(idProduct);
		Shelf shelf = repository.getEntity(idShelf);
		shelf.setProduct(product);
		repository.editEntity(shelf);
	}

	public void remProductFromShelves(long productId) {
		repository.remProductFromShelves(productId);
	}
}
