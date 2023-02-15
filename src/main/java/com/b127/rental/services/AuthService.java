package com.b127.rental.services;

import com.b127.rental.dao.UserDao;
import com.b127.rental.dao.impl.UserDaoImpl;
import com.b127.rental.entity.User;

public class AuthService extends AbstractService{

    private UserDao userDao;

    public AuthService(){
        userDao = new UserDaoImpl();
    }

    public boolean register(User user){
        if(userDao.existsUserByEmail(user.getEmail())) {
            return false;
        }else {
            return userDao.save(user);
        }
    }

    public boolean editProfile(User user) {
        return userDao.update(user, user.getId());
    }

    public User getUser(long id){
        return userDao.getById(id).get();
    }
}

