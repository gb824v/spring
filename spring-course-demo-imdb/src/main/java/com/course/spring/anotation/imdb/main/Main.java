package com.course.spring.anotation.imdb.main;

import com.course.spring.anotation.imdb.conf.ActorConfiguration;
import com.course.spring.anotation.imdb.model.Actor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;


public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ActorConfiguration.class);
        ctx.registerShutdownHook();

        Actor actor = ctx.getBean("moshe-actor", Actor.class);
        logger.info(actor.getName());


        actor = ctx.getBean("menash", Actor.class);
        actor.setName("Doron");
        logger.info(actor.getName());
    }

}
