package com.supu.repository;

import com.supu.entity.Vehicles;

import java.util.List;

public interface VehicleRepository {
    List<Vehicles> findAll();

    Vehicles findByVin(String vin);

    Vehicles create(Vehicles emp);

    Vehicles update(Vehicles emp);
}
