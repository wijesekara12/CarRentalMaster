package com.b127.rental.dao.impl;

import com.b127.rental.dao.BookingDao;
import com.b127.rental.entity.Booking;
import com.b127.rental.entity.Vehicle;
import com.b127.rental.modal.BookingModel;
import com.b127.rental.util.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoImpl extends AbstractDaoImpl implements BookingDao {

    private static Logger logger = LogManager.getLogger(BookingDaoImpl.class);

    public BookingDaoImpl() {
        super(DbUtil.getConnection());
    }

    @Override
    public boolean save(Booking booking) {
        String sql = "INSERT INTO bookings (booked_from, booked_to, no_of_vehicles, state, total_price, user_id, vehicle_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(booking.getBookedFrom()));
            preparedStatement.setDate(2, Date.valueOf(booking.getBookedTo()));
            preparedStatement.setInt(3, booking.getNoOfVehicles());
            preparedStatement.setString(4, booking.getBookingState());
            preparedStatement.setDouble(5, booking.getTotalPrice());
            preparedStatement.setLong(6, booking.getBookingOwner().getId());
            preparedStatement.setLong(7, booking.getBookedVehicle().getId());

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("Booking saving successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public boolean update(Booking booking, long id) {
        return false;
    }

    @Override
    public Optional<Booking> getById(long id) {
        String sql = "SELECT * FROM bookings WHERE id = ? LIMIT 1";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<Booking> optionalBooking = Optional.empty();
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Booking booking = new Booking(resultSet.getLong("id"),
                        null,
                        null,
                        resultSet.getDate("booked_from").toLocalDate(),
                        resultSet.getDate("booked_to").toLocalDate(),
                        resultSet.getInt("no_of_vehicles"),
                        resultSet.getDouble("price"),
                        resultSet.getString("state"));
                optionalBooking = Optional.of(booking);
                logger.debug("Booking found for id " + id);
            } else {
                logger.debug("No Booking found for id " + id);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return optionalBooking;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }


    @Override
    public List<BookingModel> getBookingsByUserId(long userId) {
        String sql = "SELECT b.id, b.booked_from, b.booked_to, v.reg_no, b.total_price, b.state FROM bookings b" +
                " INNER JOIN vehicles v ON b.vehicle_id = v.id WHERE b.user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BookingModel> bookings = new ArrayList<>();
        try {
            statement = super.getConnection().prepareStatement(sql);
            statement.setLong(1, userId);

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                BookingModel booking = new BookingModel(resultSet.getLong("id"),
                        resultSet.getDate("booked_from").toLocalDate(),
                        resultSet.getDate("booked_to").toLocalDate(),
                        resultSet.getString("reg_no"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("state"));

                bookings.add(booking);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return bookings;
    }

    @Override
    public boolean updateBookingsState(long bookingId, String state) {
        String sql = "UPDATE bookings SET state=? WHERE  id=?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, state);
            preparedStatement.setLong(2, bookingId);

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("Booking updated successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public double getTotalPriceByBookingId(long bookingId) {
        String sql = "SELECT total_price FROM bookings WHERE id = ? LIMIT 1;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = super.getConnection().prepareStatement(sql);
            statement.setLong(1, bookingId);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getDouble("total_price");
            } else {
                logger.error("No booking found for id : " + bookingId);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return 0d;
    }

    @Override
    public List<BookingModel> getBookingsByState(String state) {
        String sql = "SELECT b.id, b.booked_from, b.booked_to, v.reg_no, b.total_price, b.state FROM bookings b" +
                " INNER JOIN vehicles v ON b.vehicle_id = v.id WHERE b.state = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BookingModel> bookings = new ArrayList<>();
        try {
            statement = super.getConnection().prepareStatement(sql);
            statement.setString(1, state);

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                BookingModel booking = new BookingModel(resultSet.getLong("id"),
                        resultSet.getDate("booked_from").toLocalDate(),
                        resultSet.getDate("booked_to").toLocalDate(),
                        resultSet.getString("reg_no"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("state"));

                bookings.add(booking);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(statement, resultSet);
        }
        return bookings;
    }
}
