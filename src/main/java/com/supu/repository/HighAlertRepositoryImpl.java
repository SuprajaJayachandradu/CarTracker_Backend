package com.supu.repository;

import com.supu.entity.HighAlerts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HighAlertRepositoryImpl implements HighAlertRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public List<HighAlerts> findAllHigh() {
        TypedQuery<HighAlerts> query = entityManager.createNamedQuery("HighAlerts.findHigh",
                HighAlerts.class);
        return query.getResultList();
    }

    public HighAlerts findByVin(String vin) {
        TypedQuery<HighAlerts> query = entityManager.createNamedQuery("HighAlerts.findByVin",
                HighAlerts.class);
        query.setParameter("paramVin", vin);
        List<HighAlerts> resultList = query.getResultList();
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public void create(HighAlerts ha) {
        entityManager.persist(ha);
    }

    public HighAlerts update(HighAlerts ha) {
        return entityManager.merge(ha);
    }
}
