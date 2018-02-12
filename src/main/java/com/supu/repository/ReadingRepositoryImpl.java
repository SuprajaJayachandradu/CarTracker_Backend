package com.supu.repository;

import com.supu.entity.Reading;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Reading> findAll() {
        TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findAll",
                Reading.class);
        return query.getResultList();
    }

    public List<Reading> findByVin(String vin) {
        TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findByVin",
                Reading.class);
        query.setParameter("paramVin", vin);
        return query.getResultList();
    }

    public List<Reading> findOneMap(String vin) {
        TypedQuery<Reading> query = entityManager.createNamedQuery("Reading.findOneMap",
                Reading.class);
        query.setParameter("paramVin", vin);
        return query.getResultList();
    }

    public Reading create(Reading r) {
        entityManager.persist(r);
        return r;
    }

    public Reading update(Reading r) {
        return entityManager.merge(r);
    }
}
