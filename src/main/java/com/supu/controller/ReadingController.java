package com.supu.controller;

import com.supu.entity.Reading;
import com.supu.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/readings")
@CrossOrigin
public class ReadingController {
    @Autowired
    ReadingService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findOne(@PathVariable("vin") String vin) {
        return service.findOne(vin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/maps/{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findOneMap(@PathVariable("vin") String vin) {
        return service.findOneMap(vin);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reading create(@RequestBody Reading r) {
        return service.create(r);
    }
}