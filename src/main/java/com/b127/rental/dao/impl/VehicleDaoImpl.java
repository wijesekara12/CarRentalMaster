package com.b127.rental.dao.impl;

import com.b127.rental.dao.VehicleDao;
import com.b127.rental.entity.Vehicle;
import com.b127.rental.util.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDaoImpl extends AbstractDaoImpl implements VehicleDao {

    private static Logger logger = LogManager.getLogger(VehicleDaoImpl.class);

    public VehicleDaoImpl(){
        super(DbUtil.getConnection());
    }

    @Override
    public boolean save(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (name, reg_no, capacity, price, image, specs) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getName());
            preparedStatement.setString(2, vehicle.getRegistrationNumber());
            preparedStatement.setInt(3, vehicle.getCapacity());
            preparedStatement.setDouble(4, vehicle.getPrice());
            preparedStatement.setString(5, vehicle.getImage());
            preparedStatement.setString(6, vehicle.getSpecifications());

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("Vehicle saving successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public boolean update(Vehicle vehicle, long id) {
        String sql = "UPDATE vehicles SET name=?, reg_no=?, capacity=?, price=?, specs=? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getName());
            preparedStatement.setString(2, vehicle.getRegistrationNumber());
            preparedStatement.setInt(3, vehicle.getCapacity());
            preparedStatement.setDouble(4, vehicle.getPrice());
            preparedStatement.setString(5, vehicle.getSpecifications());
            preparedStatement.setLong(6, id);

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("Vehicle updating successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public Optional<Vehicle> getById(long id) {
        String sql = "SELECT * FROM vehicles WHERE id = ? LIMIT 1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<Vehicle> optionalVehicle = Optional.empty();
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Vehicle vehicle = new Vehicle(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("reg_no"),
                        resultSet.getInt("capacity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("specs"));
                optionalVehicle = Optional.of(vehicle);
                logger.debug("Vehicle found for id " + id);
            } else {
                logger.debug("No vehicle found for id " + id);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return optionalVehicle;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("Vehicle deleting successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public List<Vehicle> getAll() {
        String sql = "SELECT * FROM vehicles";
        Statement statement = null;
        ResultSet resultSet = null;
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            statement = super.getConnection().createStatement();

            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Vehicle vehicle = new Vehicle(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("reg_no"),
                        resultSet.getInt("capacity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("specs"));

                vehicles.add(vehicle);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesRandomly(int quantity) {
        String sql = "SELECT * FROM vehicles ORDER BY RANDOM() LIMIT ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            statement = super.getConnection().prepareStatement(sql);
            statement.setInt(1, quantity);

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Vehicle vehicle = new Vehicle(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("reg_no"),
                        resultSet.getInt("capacity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("specs"));

                vehicles.add(vehicle);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByAvailablePeriodAndCapacity(LocalDate startDate, LocalDate endDate, int capacity) {
        String sql = "SELECT * FROM vehicles WHERE capacity >= ? AND id NOT IN " +
                "(SELECT DISTINCT vehicle_id FROM bookings WHERE booked_from <= ? AND booked_to >= ?);";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            statement = super.getConnection().prepareStatement(sql);
            statement.setInt(1, capacity);
            statement.setDate(2, Date.valueOf(endDate));
            statement.setDate(3, Date.valueOf(startDate));

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Vehicle vehicle = new Vehicle(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("reg_no"),
                        resultSet.getInt("capacity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getString("specs"));

                vehicles.add(vehicle);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return vehicles;
    }

    @Override
    public Double getVehiclePerDayPriceById(long id) {
        String sql = "SELECT price FROM vehicles WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = super.getConnection().prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getDouble("price");
            } else {
                logger.error("No vehicle found for id : " + id);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return 0d;
    }
}
