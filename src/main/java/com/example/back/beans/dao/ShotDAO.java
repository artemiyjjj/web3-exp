package com.example.back.beans.dao;

import com.example.back.entities.ShotEntity;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Stateless
public class ShotDAO implements DAO<ShotEntity> {

    @PostConstruct
    private void initConnection() {
        HibernateUtil.init();
    }

    @PreDestroy
    private void deInitConnection() {
        HibernateUtil.closeSessionFactory();
    }

    public ShotDAO() {}

    @Override
    public void create(ShotEntity shot) throws IllegalStateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(shot);

            transaction.commit();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<ShotEntity> read(int id) {
        ShotEntity shotEntity;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            shotEntity = session.get(ShotEntity.class, id);
            transaction.commit();

            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        if (shotEntity == null) {
            return Optional.empty();
        }
        else {
            return Optional.of(shotEntity);
        }
    }

    @Override
    public List<ShotEntity> readAll() {
        List<ShotEntity> resultList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            resultList = session.createQuery("SELECT a FROM ShotEntity a", ShotEntity.class).getResultList();
            transaction.commit();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return resultList;
    }

    @Override
    public void update(int id, ShotEntity shotEntity) {
    }

    @Override
    public void delete(int id) {
    }

}
