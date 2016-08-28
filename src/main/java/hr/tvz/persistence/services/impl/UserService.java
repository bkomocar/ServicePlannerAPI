package hr.tvz.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hr.tvz.persistence.dao.common.IOperations;
import hr.tvz.persistence.dao.interfaces.IUserDao;
import hr.tvz.persistence.models.User;
import hr.tvz.persistence.services.common.AbstractService;
import hr.tvz.persistence.services.interfaces.IUserService;

@Service
public class UserService extends AbstractService<User> implements IUserService {

	//private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
	
    @Autowired
    private IUserDao dao;

    public UserService() {
        super();
    }

    @Override
    protected IOperations<User> getDao() {
        return dao;
    }

    @Override
    public boolean isUserValid(String name){
    	if (dao.findByName(name) != null) {
    		return true;
    	}
    	return false;
    }
}