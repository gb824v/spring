package com.att.eg.profile.mysubscriptions.info;

import com.att.eg.profile.mysubscriptions.info.util.CarouselItemConverter;
import com.att.eg.profile.mysubscriptions.info.util.ResponseBuilderUtil;
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
import com.att.eg.profile.mysubscriptions.info.model.EProfile;
import com.att.eg.profile.mysubscriptions.info.service.QPUMSClient;
import com.att.eg.profile.mysubscriptions.info.util.ColorCodeBuilder;
import com.att.eg.profile.mysubscriptions.info.util.ColorCodeBuilderImpl;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsCarouselResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsCarouselResponseBuilderImpl;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsInfoResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsInfoResponseBuilderImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"com.att.eg.profile.mysubscriptions.info", "com.att.ajsc", "com.att.eg.monitoring"})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CouchBaseDAO<EProfile> couchBaseEProfileDao(CouchbaseRepositoryFactory crf) {
		return crf.getDAO(EProfile.class);
	}
    
    @Bean
	public ColorCodeBuilder colorCodeBuilder() {
		return new ColorCodeBuilderImpl();
	}

	@Bean
	@Autowired
	public ResponseBuilderUtil responseBuilderUtil(QPUMSClient qpumsClient) {
        return new ResponseBuilderUtil(qpumsClient);
    }

    @Bean
    @Autowired
	public MySubscriptionsInfoResponseBuilder mySubscriptionsInfoResponseBuilder(CouchBaseDAO<EProfile> couchBaseDAO, ColorCodeBuilder colorCodeBuilder, QPUMSClient qpumsClient, ResponseBuilderUtil responseBuilderUtil) {
		return new MySubscriptionsInfoResponseBuilderImpl(couchBaseDAO, colorCodeBuilder, qpumsClient, responseBuilderUtil);
	}

	@Bean
	@Autowired
	public MySubscriptionsCarouselResponseBuilder mySubscriptionsCarouselResponseBuilder(MySubscriptionsInfoResponseBuilder infoResponseBuilder,
																						 CarouselItemConverter carouselItemConverter,
                                                                                         ResponseBuilderUtil responseBuilderUtil) {
		return new MySubscriptionsCarouselResponseBuilderImpl(infoResponseBuilder, carouselItemConverter, responseBuilderUtil);
	}
}