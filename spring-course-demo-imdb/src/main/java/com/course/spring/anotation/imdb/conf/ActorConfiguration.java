package com.course.spring.anotation.imdb.conf;

import com.course.spring.anotation.imdb.model.Actor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ActorConfiguration {

    @Bean(initMethod = "initializationValidation", destroyMethod = "destruction")
    public Actor menash() {
        Actor actor = new Actor(32, "David", 78);
        return actor;
    }
    @Bean(name = "moshe-actor", initMethod = "initializationValidation", destroyMethod = "destruction")
    public Actor moshe() {
        Actor actor = new Actor(22, "Moshe", 55);
        return actor;
    }
}
