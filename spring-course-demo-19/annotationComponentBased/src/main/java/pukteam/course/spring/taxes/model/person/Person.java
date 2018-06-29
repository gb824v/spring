package pukteam.course.spring.taxes.model.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pukteam.course.spring.taxes.model.Counter;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.Status;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person {

    private static Logger logger = LogManager.getLogger(Person.class);

    private int id;
    private String name;
    private int age;
    private int income;
    private Gender gender;
    private Status status;

    @Autowired
    private Counter counter;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected void init() {
        logger.info("Performing initialization validation on person {}", id);

        if (counter == null) {
            logger.error("Error ! counter wasn't injected properly. Creating it alone....");
            counter = new Counter();
        }

        if (age < 18) {
            logger.warn("Note ! person #{} is under the age of 18 (he is {} years old). No taxes for him...", id, age);
        }

        if (income < 0) {
            logger.error("Error ! person #{} has negative income !! {}", id, income);
        }
    }

    @PreDestroy
    public void destruction() {
        logger.info("Destroying person {}", id);
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

    public void setIncome(int income) {
        this.income = income;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
