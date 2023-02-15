package com.b127.rental.dao.impl;

import com.b127.rental.dao.PaymentDao;
import com.b127.rental.entity.Payment;
import com.b127.rental.util.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl extends AbstractDaoImpl implements PaymentDao {

    private static Logger logger = LogManager.getLogger(PaymentDaoImpl.class);

    public PaymentDaoImpl(){
        super(DbUtil.getConnection());
    }
    @Override
    public boolean save(Payment payment) {
        String sql = "INSERT INTO payments(amount, card_no, type, cvv, expiration, user_id, date_time, booking_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = super.getConnection().prepareStatement(sql);
            preparedStatement.setDouble(1, payment.getAmount());
            preparedStatement.setString(2, payment.getCardNo());
            preparedStatement.setString(3, payment.getCardType());
            preparedStatement.setString(4, payment.getCvv());
            preparedStatement.setString(5, payment.getExpiration());
            preparedStatement.setLong(6, payment.getUser().getId());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(8, payment.getBooking().getId());

            super.setTransactionCompleted( preparedStatement.executeUpdate() == 1 );
            logger.debug("Payment saving successful");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            closeResources(preparedStatement, null);
        }
        return super.isTransactionCompleted();
    }

    @Override
    public boolean update(Payment payment, long id) {
        return false;
    }

    @Override
    public Optional<Payment> getById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }
}
