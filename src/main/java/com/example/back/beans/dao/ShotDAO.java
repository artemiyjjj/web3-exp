package com.example.back.beans.dao;

import com.example.back.entities.ShotEntity;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Singleton
@LocalBean
public class ShotDAO implements DAO<ShotEntity> {

    @EJB
    private HibernateUtil hibernateUtil;

    public ShotDAO() {}

    @Override
    public void create(ShotEntity shot) throws IllegalStateException {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            resultList = session.createQuery("SELECT e FROM ShotEntity e", ShotEntity.class).getResultList();
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
