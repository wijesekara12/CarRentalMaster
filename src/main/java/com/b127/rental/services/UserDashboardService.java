package com.b127.rental.services;

import com.b127.rental.dao.VehicleDao;
import com.b127.rental.dao.impl.VehicleDaoImpl;
import com.b127.rental.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public class UserDashboardService extends AbstractService{

    private VehicleDao vehicleDao;

    public UserDashboardService() {
        vehicleDao = new VehicleDaoImpl();
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleDao.getAll();
    }

    public List<Vehicle> searchVehicles(LocalDate startDate, LocalDate endDate, int capacity) {
        return vehicleDao.getVehiclesByAvailablePeriodAndCapacity(startDate, endDate, capacity);
    }


}
