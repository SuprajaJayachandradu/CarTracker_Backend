package com.supu.service;

import com.supu.entity.Reading;

import java.util.List;

public interface ReadingService {
    List<Reading> findAll();
    List<Reading> findOne(String vin);
    List<Reading> findOneMap(String vin);
    Reading create(Reading r);
}
