package com.course.spring.imdb.model;

public class Movie {
    private int id;
    private String name;
    private Genre genre;
    private Actor mainActor;
    private Actor secondaryActor;

    public Movie(int id, String name, Genre genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public Actor getMainActor() {
        return mainActor;
    }

    public void setMainActor(Actor mainActor) {
        this.mainActor = mainActor;
    }

    public Actor getSecondaryActor() {
        return secondaryActor;
    }

    public void setSecondaryActor(Actor secondaryActor) {
        this.secondaryActor = secondaryActor;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Name=" + name +
                ", Genre=" + genre.toString() +
                ", MainActor=" + mainActor.toString() +
                ", SecondaryActor=" + secondaryActor.toString() +
                '}';
    }
    @Override
    public int hashCode() {
        return (int) id * name.hashCode() * genre.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return this.id == ((Movie)obj).id ;
    }
}
