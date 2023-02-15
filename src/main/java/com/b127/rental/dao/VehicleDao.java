package com.b127.rental.dao;

import com.b127.rental.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleDao extends SuperDao<Vehicle>{

    List<Vehicle> getVehiclesRandomly(int quantity);

    List<Vehicle> getVehiclesByAvailablePeriodAndCapacity(LocalDate startDate, LocalDate endDate, int capacity);

    Double getVehiclePerDayPriceById(long id);
}
