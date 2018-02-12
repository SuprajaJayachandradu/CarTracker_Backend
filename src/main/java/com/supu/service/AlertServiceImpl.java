package com.supu.service;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;
import com.supu.exception.ResourceNotFoundException;
import com.supu.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository repository;

    @Transactional(readOnly = true)
    public List<HighAlerts> findAllHigh() {
        return repository.findAllHigh();
    }

    @Transactional(readOnly = true)
    public List<Alert> findAll(String vin) {
        return repository.findAll(vin);
    }



}
