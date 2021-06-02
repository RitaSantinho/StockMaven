package io.altar.stockMaven.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.stockMaven.models.Entity2_;

public abstract class EntityRepository<E extends Entity2_> {

	@PersistenceContext(unitName = "database")
	protected EntityManager eManager;

	protected abstract Class<E> getEntityClass();

	public long addEntity(E e) {
		eManager.merge(e);
		return e.getId();
	}

	public E getEntity(long id) {
		return eManager.find(getEntityClass(), id);
	}

	public E editEntity(E e) {
		return eManager.merge(e);
	}

	public void removeEntity(long id) {
		E e = eManager.find(getEntityClass(), id);
		eManager.remove(e);
	}
}
