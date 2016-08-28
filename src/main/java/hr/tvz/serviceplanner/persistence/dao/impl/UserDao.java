package hr.tvz.serviceplanner.persistence.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.IUserDao;
import hr.tvz.serviceplanner.persistence.models.User;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

	@Override
	public User findByName(final String name){
		Criteria crit = getCurrentSession().createCriteria(User.class);  
		crit.add(Restrictions.eq("name", name));
		List results = crit.list();
		if (results != null && !results.isEmpty()) {
		return (User) results.get(0);
		}
		return null;
	}
	
	 public UserDao() {
	        super();
	        setClazz(User.class);
	    }
}
