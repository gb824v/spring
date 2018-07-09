package com.course.spring.anotation.imdb.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Actor {

    private static Logger logger = LogManager.getLogger(Actor.class);

    private int id;
    private String name;
    private int age;

    public Actor(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Actor() {
    }

    public void initializationValidation() {
        logger.info("Performing initialization validation on actor {}", id);
    }

    public void destruction() {
        logger.info("Destroying actor {}", id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "Name=" + name +
                ", Id=" + id +
                ", Age=" + age +
                '}';
    }

    @Override
    public int hashCode() {
        return (int) id * name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Actor) obj).id;
    }
}
