package com.b127.rental.services;

import com.b127.rental.dao.VehicleDao;
import com.b127.rental.dao.impl.VehicleDaoImpl;
import com.b127.rental.entity.Vehicle;

import java.util.List;

public class HomeService extends AbstractService {

    private VehicleDao vehicleDao;

    public HomeService() {
        vehicleDao = new VehicleDaoImpl();
    }

    public List<Vehicle> getVehiclesRandomlyForHomeScreen() {
        return vehicleDao.getVehiclesRandomly(3);
    }


}
