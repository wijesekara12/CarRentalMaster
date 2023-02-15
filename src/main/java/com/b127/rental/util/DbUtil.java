package com.b127.rental.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String DRIVER_CLASS = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/car_rental";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "T1122vd0";
    private static Logger logger = LogManager.getLogger(DbUtil.class);
    private static  Connection connection = null;

    private DbUtil(){}

    public static synchronized Connection getConnection(){
        if(connection == null){
            try {
                Class.forName(DRIVER_CLASS);
                if(connection == null) connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                logger.debug("Database Connection Established");
            } catch (SQLException | ClassNotFoundException ex) {
                logger.error(ex.getMessage());
            }
        }

        return connection;
    }

}
