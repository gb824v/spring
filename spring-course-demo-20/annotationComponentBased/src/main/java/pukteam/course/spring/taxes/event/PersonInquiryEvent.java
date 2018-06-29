package pukteam.course.spring.taxes.event;

import org.springframework.context.ApplicationEvent;

public class PersonInquiryEvent extends ApplicationEvent{


    private int personId;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PersonInquiryEvent(Object source, int personId) {
        super(source);
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }
}
