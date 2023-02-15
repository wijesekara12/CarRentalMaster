package com.b127.rental.dao.impl;

import com.b127.rental.dao.UserDao;
import com.b127.rental.entity.User;
import com.b127.rental.entity.Vehicle;
import com.b127.rental.util.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(DbUtil.getConnection());
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password){
        String sql = "SELECT id, email, role FROM users WHERE email =? AND password =? LIMIT 1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User(resultSet.getLong("id"),
                        "",
                        resultSet.getString("email"),
                        "",
                        "",
                        resultSet.getString("role") );
                optionalUser = Optional.of(user);
                logger.debug("User found for email " + email);
            } else {
                logger.debug("No user found for email " + email);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return optionalUser;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        String sql = "SELECT COUNT(id) FROM users WHERE email = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            return resultSet.getInt("count") == 1;

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO users (name, email, password, role, mobile) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getMobile());

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("User saving successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public boolean update(User user, long id) {
        String sql = "UPDATE users SET name=?, email=?, mobile=? WHERE id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setLong(4, id);

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("User updating successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    public Optional<User> getById(long id) {
        String sql = "SELECT name, email, mobile FROM users WHERE id = ? LIMIT 1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User(0L,
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("mobile"),
                        "", "");
                optionalUser = Optional.of(user);
                logger.debug("User found for id " + id);
            } else {
                logger.debug("No user found for id " + id);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return optionalUser;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
