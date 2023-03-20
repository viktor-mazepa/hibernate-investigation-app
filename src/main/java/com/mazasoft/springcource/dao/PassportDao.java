package com.mazasoft.springcource.dao;

import com.mazasoft.springcource.model.Passport;
import com.mazasoft.springcource.model.Person;
import com.mazasoft.springcource.utils.HibernateSessionFactory;
import org.hibernate.Session;

public class PassportDao {

    public Passport selectPassportByPerson(Integer personId) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Passport passport = session.get(Passport.class, personId);
        System.out.println(passport);
        session.getTransaction().commit();
        session.close();
        return passport;
    }

    public void updatePassportForPerson(Integer personId, Integer newPassportNumber) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Person person = session.get(Person.class, personId);
        person.getPassport().setPassportNumber(newPassportNumber);
        session.getTransaction().commit();
        session.close();
    }
}
