package io.altar.stockMaven.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.stockMaven.models.Product;
import io.altar.stockMaven.models.Shelf;
import io.altar.stockMaven.repositories.ProductRepository;

@RequestScoped
public class ProductService extends EntityService<ProductRepository, Product> {

	@Inject
	ShelfService shelfS;

	@Override
	public void removeEntity(long id) {
		removeFromAllShelves(id);
		repository.removeEntity(id);
	}

	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}

	public long howManyProducts() {
		return repository.howManyProducts();
	}

	public List<Shelf> whereIsProduct(long id) {
		return shelfS.getShelvesWithProduct(id);
	}

	public void removeProductFromShelves(List<Long> shelvesIdsList) {
		for (int i = 0; i < shelvesIdsList.size(); i++) {
			long shelfId = shelvesIdsList.get(i);
			shelfS.removeProductFromShelf(shelfId);
		}
	}

	public void addProductToShelves(long idProduct, List<Long> shelvesIdsList) {
		for (int i = 0; i < shelvesIdsList.size(); i++) {
			long shelfId = shelvesIdsList.get(i);
			shelfS.addProductToShelf(shelfId, idProduct);
		}
	}

	public void removeFromAllShelves(long id) {
		List<Shelf> shelves = shelfS.getShelvesWithProduct(id);
		List<Long> shelvesIds = new ArrayList<Long>();
		for (int i = 0; i < shelves.size(); i++) {
			shelvesIds.add(shelves.get(i).getId());
		}
		removeProductFromShelves(shelvesIds);
	}

	public List<Product> getProductsOrderedByKeyAsc(String key) throws Exception {
		return repository.getProductsOrderedByKeyAsc(key);
	}

	public List<Product> getProductsOrderedByKeyDesc(String key) throws Exception {
		return repository.getProductsOrderedByKeyDesc(key);
	}

	public List<Product> getProductsByKeyValue(float keyValue, String key) throws Exception {
		return repository.getProductsByKeyValue(keyValue, key);
	}
}
