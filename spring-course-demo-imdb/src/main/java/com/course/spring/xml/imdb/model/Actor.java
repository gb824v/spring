package com.course.spring.xml.imdb.model;

public class Actor {

    private int id;
    private String name;
    private int age;

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
