package com.mazasoft.springcource;


import com.mazasoft.springcource.dao.PersonDao;
import com.mazasoft.springcource.utils.HibernateSessionFactory;

public class App {

    public static void main(String[] args) {
        try {
            new PersonDao().selectAll();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            HibernateSessionFactory.close();
        }
    }
}
