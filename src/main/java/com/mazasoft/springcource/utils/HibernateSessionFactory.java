package com.mazasoft.springcource.utils;

import com.mazasoft.springcource.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                        .addAnnotatedClass(Item.class)
                        .addAnnotatedClass(Passport.class)
                        .addAnnotatedClass(Movie.class)
                        .addAnnotatedClass(Actor.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Something happened during session initialization " + e);
            }
        }
        return sessionFactory.getCurrentSession();
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

