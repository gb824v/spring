package pukteam.course.spring.taxes.model;

public class Person {

    private int id;
    private int age;
    private int income;
    private Gender gender;
    private Status status;

    public Person() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getIncome() {
        return income;
    }

    public Gender getGender() {
        return gender;
    }

    public Status getStatus() {
        return status;
    }
}
