package com.supu.service;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;

import java.util.List;

public interface AlertService {
    List<HighAlerts> findAllHigh();
    List<Alert> findAll(String vin);
}
