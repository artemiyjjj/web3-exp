package com.example.back.beans.dao;

import com.example.back.entities.ShotEntity;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static void init() throws HibernateException {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure()
                .addAnnotatedClass(ShotEntity.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
                System.out.println("jopa");
            }
        }
    }
}
