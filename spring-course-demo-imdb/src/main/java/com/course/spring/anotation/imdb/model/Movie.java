package com.course.spring.anotation.imdb.model;

import java.util.List;

public class Movie {
    private int id;
    private String name;
    private Genre genre;
    private List<Actor> actors;

    public Movie(int id, String name, Genre genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Name=" + name +
                ", Genre=" + genre.toString() +
                ", Actors=" + actors.toString() +
                '}';
    }

    @Override
    public int hashCode() {
        return (int) id * name.hashCode() * genre.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Movie) obj).id;
    }
}
