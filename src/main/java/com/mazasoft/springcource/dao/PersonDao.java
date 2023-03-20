package com.mazasoft.springcource.dao;

import com.mazasoft.springcource.model.Item;
import com.mazasoft.springcource.model.Passport;
import com.mazasoft.springcource.model.Person;
import com.mazasoft.springcource.utils.HibernateSessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.Collection;

public class PersonDao {

    private static final String DUMMY_ITEM_NAME = "Item from Hibernate";
    private static final String DUMMY_PERSON_NAME = "Person from Hibernate";
    private static final Integer DUMMY_PASSPORT_NUMBER = 123456789;

    public void insertDummyPerson() {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Person person = new Person(DUMMY_PERSON_NAME, 23);
        Passport passport = new Passport(DUMMY_PASSPORT_NUMBER);
        person.setPassport(passport);
        person.addItem(new Item(DUMMY_ITEM_NAME));
        session.save(person);
        session.getTransaction().commit();
        session.close();
    }

    public Person select(Integer id) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        System.out.println(person);
        System.out.println(person.getItems());
        System.out.println(person.getPassport());
        session.getTransaction().commit();
        session.close();

        return person;
    }

    public void update(Integer id) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        person.setName(DUMMY_PERSON_NAME);
        System.out.println("simple-hibernate-app: person.name.update = " + person.getName());
        System.out.println("simple-hibernate-app: person.age = " + person.getAge());
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Integer id) {
        Session session = HibernateSessionFactory.getSession();

        Person person = session.get(Person.class, id);
        session.remove(person);
        person.getItems().forEach(item -> item.setOwner(null));
        session.getTransaction().commit();
        session.close();
    }

    public Collection<Person> selectAll() {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Collection<Person> people = session.createQuery("FROM Person").list();
        people.forEach(person -> System.out.println(person));
        session.getTransaction().commit();
        session.close();
        return people;
    }

    public Collection<Person> executeHql() {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        session.createQuery("update Person set name=\'Test\' where age<30").executeUpdate();
        Collection<Person> people = session.createQuery("FROM Person WHERE age<30").getResultList();
        session.createQuery("delete from Person where age>30").executeUpdate();
        session.getTransaction().commit();
        session.close();
        return people;
    }
}
