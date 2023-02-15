package com.b127.rental.services;

import com.b127.rental.dao.UserDao;
import com.b127.rental.dao.VehicleDao;
import com.b127.rental.dao.impl.UserDaoImpl;
import com.b127.rental.dao.impl.VehicleDaoImpl;
import com.b127.rental.entity.User;
import com.b127.rental.entity.Vehicle;
import com.b127.rental.modal.VehicleAndUserDetailsModel;

import java.util.Optional;

public class DetailsService extends AbstractService {

    private VehicleDao vehicleDao;
    private UserDao userDao;

    public DetailsService(){
        this.vehicleDao = new VehicleDaoImpl();
        this.userDao = new UserDaoImpl();
    }

    public Optional<VehicleAndUserDetailsModel> getVehicleAndUserDetails(long vehicleId, long userId){
        Optional<Vehicle> optionalVehicle = vehicleDao.getById(vehicleId);
        Optional<User> optionalUser = userDao.getById(userId);

        Optional<VehicleAndUserDetailsModel> optionalVehicleAndUserDetailsModel = Optional.empty();

        if(optionalVehicle.isPresent() && optionalUser.isPresent()) {
            User user = optionalUser.get();
            VehicleAndUserDetailsModel vehicleAndUserDetailsModel = new VehicleAndUserDetailsModel(optionalVehicle.get(),
                    user.getName(),
                    user.getEmail(),
                    user.getMobile());
            optionalVehicleAndUserDetailsModel = Optional.of(vehicleAndUserDetailsModel);
        }

        return optionalVehicleAndUserDetailsModel;

    }

}
