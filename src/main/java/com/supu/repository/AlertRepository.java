package com.supu.repository;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;

import java.util.List;

public interface AlertRepository{

    List<HighAlerts> findAllHigh();
    List<Alert> findAll(String vin);
    void create(Alert ar);
}
