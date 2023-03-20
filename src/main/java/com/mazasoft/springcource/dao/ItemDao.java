package com.mazasoft.springcource.dao;

import com.mazasoft.springcource.model.Item;
import com.mazasoft.springcource.model.Person;
import com.mazasoft.springcource.utils.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Collection;

public class ItemDao {
    private static final String DUMMY_ITEM_NAME = "Item from Hibernate";

    public void insertDummyItemForPerson(Integer personId) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Person person = session.get(Person.class, personId);
        Item item = new Item(DUMMY_ITEM_NAME);
        person.addItem(item);
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public Item select(Integer id) {
        Item item = null;
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        item = session.get(Item.class, id);
        System.out.println(item);
        System.out.println(item.getOwner());
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void update(Integer id, Integer personId) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        Person person = session.get(Person.class, personId);
        item.setOwner(person);
        person.getItems().add(item);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteItemsForPerson(Integer personId) {

        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Person person = session.get(Person.class, personId);
        Collection<Item> items = person.getItems();
        for (Item item : items) {
            session.remove(item);
        }
        person.getItems().clear();
        session.getTransaction().commit();
        session.close();
    }
}
