package com.mazasoft.springcource.dao;

import com.mazasoft.springcource.model.Actor;
import com.mazasoft.springcource.model.Movie;
import com.mazasoft.springcource.utils.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MovieDao {

    public void insertDummyMovie() {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Movie movie = new Movie("Pulp fiction", 1994);
        Actor actor1 = new Actor("Harvey Keitel", 81);
        Actor actor2 = new Actor("Samuel L. Jackson", 72);
        movie.setActors(Arrays.asList(actor1, actor2));
        actor1.setMovies(new ArrayList<>(Collections.singleton(movie)));
        actor2.setMovies(new ArrayList<>(Collections.singleton(movie)));
        session.save(movie);
        session.getTransaction().commit();
        session.close();
    }

    public Movie select(Integer id) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        Movie movie = session.get(Movie.class, id);
        System.out.println(movie);
        movie.getActors().forEach(actor -> System.out.println(actor));
        session.getTransaction().commit();
        session.close();
        return movie;
    }
}
