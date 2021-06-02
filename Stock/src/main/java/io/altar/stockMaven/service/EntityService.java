package io.altar.stockMaven.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.stockMaven.models.Entity2_;
import io.altar.stockMaven.repositories.EntityRepository;

@Transactional
public abstract class EntityService<R extends EntityRepository<E>, E extends Entity2_> {

	@Inject
	protected R repository;

	public long addEntity(E e) {

		return repository.addEntity(e);

	}

	public E getEntity(long id) {
		return repository.getEntity(id);
	}

	public E editEntity(E e) {
		return repository.editEntity(e);
	}

	public void removeEntity(long id) {
		repository.removeEntity(id);
	}
}
