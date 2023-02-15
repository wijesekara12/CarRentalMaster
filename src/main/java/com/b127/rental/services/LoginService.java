package com.b127.rental.services;

import com.b127.rental.dao.UserDao;
import com.b127.rental.dao.impl.UserDaoImpl;
import com.b127.rental.entity.User;

import java.util.Optional;

public class LoginService {

    private UserDao userDao;

    public  LoginService(){
        this.userDao = new UserDaoImpl();
    }

    public Optional<User> loginUser(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }
}
