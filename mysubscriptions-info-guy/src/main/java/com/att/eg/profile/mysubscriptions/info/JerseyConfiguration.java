package com.att.eg.profile.mysubscriptions.info;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.att.ajsc.common.AbstractJerseyConfiguration;
import com.att.ajsc.common.messaging.LogRequestFilter;

import com.att.eg.profile.mysubscriptions.info.service.rs.RestServiceImpl;

@Component
@ApplicationPath("/")
public class JerseyConfiguration extends AbstractJerseyConfiguration {
    private static final Logger log = Logger.getLogger(JerseyConfiguration.class.getName());

    @Value("${server.contextPath:/}")
    private String serverContextPath;

    @PostConstruct
    private void configure() {
        this.configureSwagger();
    }

    @Autowired
    public JerseyConfiguration(LogRequestFilter lrf,
                               @Value("${ajsc.jersey.loggingfilter.enabled:false}") boolean jerseyLoggingFilterEnabled,
                               @Value("${ajsc.jersey.loggingfilter.printentity.enabled:false}") boolean jerseyLoggingFilterPrintEntityEnabled) {

        super(log, lrf, jerseyLoggingFilterEnabled, jerseyLoggingFilterPrintEntityEnabled);
        this.registerEndpoints();
    }

    private void registerEndpoints() {
        register(RestServiceImpl.class);
    }

    private void configureSwagger() {

        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setConfigId("springboot-jersey-swagger");
        swaggerConfig.setTitle("Service Rest API"); // Change to your title, if desired
        swaggerConfig.setVersion("1.0.0"); // Change to your version, if desired
        swaggerConfig.setContact("Osprey"); // Change to your team name, if desired
        swaggerConfig.setSchemes(new String[]{"http", "https"});
        swaggerConfig.setBasePath(this.serverContextPath);
        swaggerConfig.setResourcePackage("com.att.eg.profile.mysubscriptions.info"); // Scans package and sub-packages for Swagger annotated classes. Very important.
        swaggerConfig.setPrettyPrint(true);
        swaggerConfig.setScan(true);
        SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);
    }
}