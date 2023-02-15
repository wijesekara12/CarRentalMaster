package com.b127.rental.services;

import com.b127.rental.dao.VehicleDao;
import com.b127.rental.dao.impl.VehicleDaoImpl;
import com.b127.rental.entity.Vehicle;

import java.util.List;

public class VehicleService {

    private VehicleDao vehicleDao;

    public VehicleService(){
        this.vehicleDao = new VehicleDaoImpl();
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAll();
    }

    public Vehicle getVehicleById(long id){
        return vehicleDao.getById(id).get();
    }

    public boolean updateVehicle(Vehicle vehicle, long id) {
        return vehicleDao.update(vehicle, id);
    }

    public boolean deleteVehicle(long id) {
        return vehicleDao.delete(id);
    }

    public boolean addVehicle(Vehicle vehicle){
        return vehicleDao.save(vehicle);
    }
}
