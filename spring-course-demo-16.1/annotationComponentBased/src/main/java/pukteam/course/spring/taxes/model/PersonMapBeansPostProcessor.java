package pukteam.course.spring.taxes.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import pukteam.course.spring.taxes.model.person.Person;

import java.util.HashSet;
import java.util.Set;

public class PersonMapBeansPostProcessor implements BeanPostProcessor {

    private static Logger logger = LogManager.getLogger(PersonMapBeansPostProcessor.class);
    private Set<Integer> knownPersonIds;

    public PersonMapBeansPostProcessor() {
        knownPersonIds = new HashSet<>();
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Person) {

            int personId = ((Person)bean).getId();
            logger.info("Bean post processor: Processing person ID {}..", personId);
            if (knownPersonIds.contains(personId)) {
                logger.error("Bean post processor: person ID {} already exists !", personId);
            } else {
                knownPersonIds.add(personId);
                logger.info("Bean post processor: Accepting person ID {}.. so far encountered {} persons", personId, knownPersonIds.size());
            }
        }

        return bean;
    }
}
