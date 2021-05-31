package io.altar.stockMaven.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.altar.stockMaven.models.Shelf;

@ApplicationScoped
public class ShelfRepository extends EntityRepository<Shelf> {

	@Override
	protected Class<Shelf> getEntityClass() {

		return Shelf.class;
	}

	public long howManyShelves() {
		return eManager.createNamedQuery("howManyShelves", Long.class).getSingleResult();
	}

	public List<Shelf> getAllShelves() {
		return eManager.createNamedQuery("getAllShelves", getEntityClass()).getResultList();
	}

	public List<Shelf> getShelvesWithProduct(long id) {
		return eManager.createNamedQuery("getShelvesByProductId", getEntityClass()).setParameter("id", id)
				.getResultList();
	}

	public List<Shelf> getEmptyShelves() {
		return eManager.createNamedQuery("getEmptyShelves", getEntityClass()).getResultList();
	}

	public void remProductFromShelves(long productId) {
		eManager.createNamedQuery("remProductFromShelves").setParameter("productId", productId).executeUpdate();
	}
}
