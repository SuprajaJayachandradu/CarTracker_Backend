package com.supu.service;

import com.supu.email.Main;
import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;
import com.supu.entity.Reading;
import com.supu.entity.Vehicles;
import com.supu.repository.AlertRepository;
import com.supu.repository.HighAlertRepository;
import com.supu.repository.ReadingRepository;
import com.supu.repository.VehicleRepository;
import com.supu.rules.CoolantEngineRule;
import com.supu.rules.FuelVolumeRule;
import com.supu.rules.RedLineRpmRule;
import com.supu.rules.TirePressureRule;
import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

@Service
public class ReadingServiceImpl implements ReadingService{

    @Autowired
    ReadingRepository repository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    HighAlertRepository highAlertRepository;

    @Transactional(readOnly = true)
    public List<Reading> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Reading> findOne(String vin) {
        return repository.findByVin(vin);
    }

    @Transactional(readOnly = true)
    public List<Reading> findOneMap(String vin) {
        return repository.findOneMap(vin);
    }

    @Transactional
    public Reading create(Reading r) {

        //Rules for Alerts Triggering point
        Vehicles vehicles = vehicleRepository.findByVin(r.getVin());

        RedLineRpmRule redLineRpmRule = new RedLineRpmRule();
        FuelVolumeRule fuelVolumeRule = new FuelVolumeRule();
        TirePressureRule tirePressureRule = new TirePressureRule();
        CoolantEngineRule coolantEngineRule = new CoolantEngineRule();
        RulesEngine rulesEngine = aNewRulesEngine()
                .withSkipOnFirstAppliedRule(true)
                .withSilentMode(true)
                .build();

        rulesEngine.registerRule(redLineRpmRule);
        rulesEngine.registerRule(fuelVolumeRule);
        rulesEngine.registerRule(tirePressureRule);
        rulesEngine.registerRule(coolantEngineRule);

        redLineRpmRule.setInput(r.getEngineRpm(),vehicles.getRedlineRpm());
        fuelVolumeRule.setInput(r.getFuelVolume(),vehicles.getMaxFuelVolume());
        tirePressureRule.setInput(r.getTires().getFrontLeft(),r.getTires().getFrontRight(),r.getTires().getRearLeft(),r.getTires().getRearRight());
        coolantEngineRule.setInput(r.isEngineCoolantLow(),r.isCheckEngineLightOn());

        rulesEngine.fireRules();

        if(redLineRpmRule.isPassOrFail()){
            HighAlerts highAlerts = highAlertRepository.findByVin(r.getVin());
            if(highAlerts!=null){
                HighAlerts ha = new HighAlerts();
                ha.setVin(r.getVin());

                long diff = ha.getTimestamp().getTime() - highAlerts.getTimestamp().getTime();

                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / ( 60 * 60 * 1000) % 24;
                long diffDays = diff / ( 24 * 60 * 60 * 1000);

                long difference = diffMinutes + diffHours * 60 + diffDays * 60 * 24;
                if(difference >30){
                    ha.setCount(1);
                }else{
                    ha.setCount(highAlerts.getCount()+1);
                }
                highAlertRepository.update(ha);
            }else{
                HighAlerts ha = new HighAlerts();
                ha.setVin(r.getVin());
                ha.setCount(1);
                highAlertRepository.create(ha);
            }
            Alert alertRecord = new Alert();
            alertRecord.setVin(r.getVin());
            alertRecord.setType("Greater Engine RPM");
            alertRecord.setPriority("HIGH");
            alertRepository.create(alertRecord);
            try {
                new Main().sendNewEmail("Vehicle - "+r.getVin()+" has a high RPM level of "+r.getEngineRpm()+". Please check!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(fuelVolumeRule.isPassOrFail()){
            Alert alertRecord = new Alert();
            alertRecord.setVin(r.getVin());
            alertRecord.setType("Lesser Fuel Volume");
            alertRecord.setPriority("MEDIUM");
            alertRepository.create(alertRecord);
        }
        if(tirePressureRule.isPassOrFail()){
            Alert alertRecord = new Alert();
            alertRecord.setVin(r.getVin());
            alertRecord.setType("Tire Pressure not 32");
            alertRecord.setPriority("LOW");
            alertRepository.create(alertRecord);
        }
        if(coolantEngineRule.isPassOrFail()){
            Alert alertRecord = new Alert();
            alertRecord.setVin(r.getVin());
            alertRecord.setType("Check Engine Coolant or Check Engine Light");
            alertRecord.setPriority("LOW");
            alertRepository.create(alertRecord);
        }
        return repository.create(r);
    }
}
