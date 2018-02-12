package com.supu.service;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;

import java.util.List;

public interface HighAlertService {
    List<HighAlerts> findAllHigh();
}
