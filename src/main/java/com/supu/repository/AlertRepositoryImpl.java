package com.supu.repository;

import com.supu.entity.Alert;
import com.supu.entity.HighAlerts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AlertRepositoryImpl implements AlertRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public List<HighAlerts> findAllHigh() {
        TypedQuery<HighAlerts> query = entityManager.createNamedQuery("Alerts.findHigh",
                HighAlerts.class);
        return query.getResultList();
    }

    public List<Alert> findAll(String vin) {
        TypedQuery<Alert> query = entityManager.createNamedQuery("Alerts.findAllByVin",
                Alert.class);
        query.setParameter("paramVin", vin);
        return query.getResultList();
    }

    public void create(Alert ar) {
        entityManager.persist(ar);
    }
}
