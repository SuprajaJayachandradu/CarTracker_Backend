package com.supu.controller;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;
import com.supu.service.AlertService;
import com.supu.service.HighAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alerts")
@CrossOrigin
public class AlertController {

    @Autowired
    AlertService service;

    @Autowired
    HighAlertService highAlertService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<HighAlerts> findAll() {
        return highAlertService.findAllHigh();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAll(@PathVariable("vin") String vin) {
        return service.findAll(vin);
    }



}
