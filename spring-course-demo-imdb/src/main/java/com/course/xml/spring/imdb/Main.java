package com.course.xml.spring.imdb;

import com.course.xml.spring.imdb.model.Actor;
import com.course.xml.spring.imdb.model.Movie;
import com.course.xml.spring.imdb.model.MovieList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Actor guy = ctx.getBean("guyId", Actor.class);
        Actor ilan = ctx.getBean("ilanId", Actor.class);
        Actor doron = ctx.getBean("hilaId", Actor.class);
        logger.debug(guy);
        logger.debug(ilan);
        logger.debug(doron);

        Movie parkHyura = ctx.getBean("parkHyuraId",Movie.class);
        Movie starWar = ctx.getBean("starWarId",Movie.class);

        logger.debug(parkHyura);
        logger.debug(starWar);

        MovieList oceans8 = ctx.getBean("Oceans-8",MovieList.class);

        logger.debug(oceans8);

        Map<String, Actor> actorsList = ctx.getBean("actorsMap", Map.class);
        Map<String, Movie> movieList = ctx.getBean("movieMap", Map.class);



    }
}
