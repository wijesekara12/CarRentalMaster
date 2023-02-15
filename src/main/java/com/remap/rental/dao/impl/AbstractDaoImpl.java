package com.b127.rental.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

abstract class AbstractDaoImpl {

    private static Logger logger = LogManager.getLogger(AbstractDaoImpl.class);
    private Connection connection ;
    private boolean isTransactionCompleted;

    public AbstractDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeResources(Statement statement, ResultSet resultSet){
        try {
            if(statement != null) statement.close();
            if(resultSet != null) resultSet.close();
            logger.debug("Successfully closed the database resources");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void setTransactionCompleted(boolean transactionCompleted) {
        isTransactionCompleted = transactionCompleted;
    }

    public boolean isTransactionCompleted() {
        return isTransactionCompleted;
    }
}
