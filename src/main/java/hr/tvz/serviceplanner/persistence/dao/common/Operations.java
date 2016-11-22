package hr.tvz.serviceplanner.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

public interface Operations<T extends Serializable> {

	T findOne(final long id);

	List<T> findAll();

	T create(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final long entityId);

	T findByName(final String name);
}
