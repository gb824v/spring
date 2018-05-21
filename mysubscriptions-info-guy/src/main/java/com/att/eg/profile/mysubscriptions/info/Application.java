package com.att.eg.profile.mysubscriptions.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.att.ajsc.common.couchbase.repository.impl.CouchBaseDAO;
import com.att.ajsc.common.couchbase.repository.impl.CouchbaseRepositoryFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"com.att.eg.profile.mysubscriptions.info", "com.att.ajsc", "com.att.eg.monitoring"})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}