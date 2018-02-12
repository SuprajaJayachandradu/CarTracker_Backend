package com.supu.service;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;
import com.supu.repository.AlertRepository;
import com.supu.repository.HighAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HighAlertServiceImpl implements HighAlertService {

    @Autowired
    HighAlertRepository repository;

    @Transactional(readOnly = true)
    public List<HighAlerts> findAllHigh() {
        return repository.findAllHigh();
    }



}
