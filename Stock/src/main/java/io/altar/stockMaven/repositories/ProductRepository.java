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

	public List<Product> getProductsByDiscount(float pDiscount) {
		return eManager.createNamedQuery("getProductsByDiscount", getEntityClass()).setParameter("pDiscount", pDiscount)
				.getResultList();
	}

}
