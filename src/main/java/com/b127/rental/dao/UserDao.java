package com.b127.rental.dao;

import com.b127.rental.entity.User;

import java.util.Optional;

public interface UserDao extends SuperDao<User> {

    Optional<User> getById(long id);

    Optional<User> getUserByEmailAndPassword(String email, String password);

    boolean existsUserByEmail(String email);

}
