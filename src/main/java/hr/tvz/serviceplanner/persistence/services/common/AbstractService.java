package hr.tvz.serviceplanner.persistence.services.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;

@Transactional
public abstract class AbstractService<T extends Serializable> implements Operations<T> {

	@Override
	public T findOne(final long id) {
		return getDao().findOne(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public T create(final T entity) {
		return getDao().create(entity);
	}

	@Override
	public T update(final T entity) {
		return getDao().update(entity);
	}

	@Override
	public void delete(final T entity) {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(final long entityId) {
		getDao().deleteById(entityId);
	}

	@Override
	public T findByName(final String name) {
		return getDao().findByName(name);
	}

	protected abstract Operations<T> getDao();

}