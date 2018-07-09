package com.course.spring.anotation.imdb.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

public class ActorMapBeansPostProcessor implements BeanPostProcessor {

    private static Logger logger = LogManager.getLogger(ActorMapBeansPostProcessor.class);
    private Set<Integer> knownActorIds;

    public ActorMapBeansPostProcessor() {
        knownActorIds = new HashSet<>();
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Actor) {

            int actorId = ((Actor)bean).getId();
            logger.info("Bean post processor: Processing actor ID {}..", actorId);
            if (knownActorIds.contains(actorId)) {
                logger.error("Bean post processor: actor ID {} already exists !", actorId);
            } else {
                knownActorIds.add(actorId);
                logger.info("Bean post processor: Accepting person ID {}.. so far encountered {} actors", actorId, knownActorIds.size());
            }
        }

        return bean;
    }
}
