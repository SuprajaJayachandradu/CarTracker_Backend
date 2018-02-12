package com.supu.service;

import com.supu.entity.Vehicles;

import java.util.List;

public interface VehicleService {
    List<Vehicles> findAll();
    Vehicles findOne(String vin);
    Vehicles update(List<Vehicles> v);
}
