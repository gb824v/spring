package pukteam.course.spring.taxes.model;

public class Person {

    private int id;
    private int age;
    private int income;
    private Gender gender;
    private Status status;
    private Counter counter;

    public Person(int id, int income) {
        this.id = id;
        this.income = income;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public void setAge(int age) {
        this.age = age;
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
        counter.increaseCounter();
        return income;
    }

    public Gender getGender() {
        return gender;
    }

    public Status getStatus() {
        return status;
    }

    public int getCounterForIncomeRequests() {
        return counter.getCount();
    }
}
