package hr.tvz.serviceplanner.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;

import hr.tvz.serviceplanner.persistence.models.User;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements Operations<T> {
	private Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	// API

	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = Preconditions.checkNotNull(clazzToSet);
	}

	@Override
	public final T findOne(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@Override
	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@Override
	public final T create(final T entity) {
		Preconditions.checkNotNull(entity);
		// getCurrentSession().persist(entity);
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public final T update(final T entity) {
		Preconditions.checkNotNull(entity);
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public final void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	@Override
	public final void deleteById(final long entityId) {
		final T entity = findOne(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}

	@Override
	public T findByName(final String name) {
		Criteria crit = getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("name", name));
		List<?> results = crit.list();
		if (results != null && !results.isEmpty()) {
			return (T) results.get(0);
		}
		return null;
	}

}