package pukteam.course.spring.taxes.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {

    private static Logger logger = LogManager.getLogger(Person.class);

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

    public void initializationValidation() {
        logger.info("Performing initialization validation on person {}", id);
        if (age < 18) {
            logger.warn("Note ! person #{} is under the age of 18 (he is {} years old). No taxes for him...", id, age);
        }

        if (income < 0) {
            logger.error("Error ! person #{} has negative income !! {}", id, income);
        }
    }
    
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

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getIncome() {
        //counter.increaseCounter();
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
