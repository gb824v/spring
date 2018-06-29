package pukteam.course.spring.taxes.model.person.instance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pukteam.course.spring.taxes.model.Gender;
import pukteam.course.spring.taxes.model.person.Person;
import pukteam.course.spring.taxes.model.Status;

import javax.annotation.PostConstruct;

@Component
public class Menash extends Person {

    @Value("${menash.age}")
    private int age;

    @Value("${menash.gender}")
    private Gender gender;

    @Value("${menash.status}")
    private Status status;

    @Value("${menash.income}")
    private int income;

    public Menash() {
        super(1, "menash");
    }

    @PostConstruct
    public void init() {
        this.setIncome(income);
        this.setAge(age);
        this.setGender(gender);
        this.setStatus(status);
        super.init();
    }
}
