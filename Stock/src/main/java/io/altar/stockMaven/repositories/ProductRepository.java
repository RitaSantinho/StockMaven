package io.altar.stockMaven.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.altar.stockMaven.models.Product;

@ApplicationScoped
public class ProductRepository extends EntityRepository<Product> {
	@Override
	protected Class<Product> getEntityClass() {

		return Product.class;
	}

	public List<Product> getAllProducts() {
		return eManager.createNamedQuery("getAllProducts", getEntityClass()).getResultList();
	}

	public long howManyProducts() {
		return eManager.createNamedQuery("howManyProducts", Long.class).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsOrderedByKeyAsc(String key) throws Exception {
		if (key.equals("pvp") || key.equals("discount") || key.equals("iva")) {
			String query = "SELECT p FROM Product p ORDER BY p." + key + " ASC";
			return eManager.createQuery(query).getResultList();
		} else {
			throw new IllegalArgumentException("Invalid key");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsOrderedByKeyDesc(String key) throws Exception {
		if (key.equals("pvp") || key.equals("discount") || key.equals("iva")) {
			String query = "SELECT p FROM Product p ORDER BY p." + key + " DESC";
			return eManager.createQuery(query).getResultList();
		} else {
			throw new IllegalArgumentException("Invalid key");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByKeyValue(float keyValue, String key) throws Exception {
		if (key.equals("pvp") || key.equals("iva") || key.equals("discount")) {
			String query = "SELECT p FROM Product p WHERE p." + key + " = " + keyValue;
			return eManager.createQuery(query).getResultList();
		} else {
			throw new IllegalArgumentException("Invalid key");
		}
	}
}
