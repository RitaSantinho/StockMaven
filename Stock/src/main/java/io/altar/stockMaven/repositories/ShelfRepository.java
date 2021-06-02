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

	@SuppressWarnings("unchecked")
	public List<Shelf> getShelvesByKeyValue(float keyValue, String key) throws Exception {
		if (key.equals("capacity") || key.equals("price")) {
			String query = "SELECT s FROM Shelf s WHERE s." + key + " = " + keyValue;
			return eManager.createQuery(query).getResultList();
		} else {
			throw new IllegalArgumentException("Invalid key");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Shelf> getShelvesOrderedByKeyAsc(String key) throws Exception {
		if (key.equals("capacity") || key.equals("price")) {
			String query = "SELECT s FROM Shelf s ORDER BY s." + key + " ASC";
			return eManager.createQuery(query).getResultList();
		} else {
			throw new IllegalArgumentException("Invalid key");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Shelf> getShelvesOrderedByKeyDesc(String key) throws Exception {
		if (key.equals("capacity") || key.equals("price")) {
			String query = "SELECT s FROM Shelf s ORDER BY s." + key + " DESC";
			return eManager.createQuery(query).getResultList();
		} else {
			throw new IllegalArgumentException("Invalid key");
		}
	}
}
