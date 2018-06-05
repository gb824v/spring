package com.att.eg.profile.mysubscriptions.info;

import com.att.ajsc.common.couchbase.repository.CouchbaseDatastore;
import com.att.ajsc.common.couchbase.repository.impl.CouchBaseDAO;
import com.att.ajsc.common.couchbase.repository.impl.CouchbaseRepositoryFactory;
import com.att.eg.profile.mysubscriptions.info.model.EProfile;
import com.att.eg.profile.mysubscriptions.info.service.QPUMSClient;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsCarouselResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsInfoResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.ResponseBuilderUtil;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockProvider {

	//Let's have all mocks here and define the rule:
	//Test should auto-wire dependencies for all the classes except the tested class
	//Tested class should be instantiated in test and injected with auto-wired mocks

	@Mock
    MySubscriptionsInfoResponseBuilder infoBuilder = Mockito.mock(MySubscriptionsInfoResponseBuilder.class);

	@Mock
    MySubscriptionsCarouselResponseBuilder carouselBuilder = Mockito.mock(MySubscriptionsCarouselResponseBuilder.class);

	@Mock
    CouchBaseDAO<EProfile> dao = Mockito.mock(CouchBaseDAO.class);

	@Mock
    QPUMSClient qpumsClient = Mockito.mock(QPUMSClient.class);

	@Mock
    ResponseBuilderUtil responseBuilderUtil = Mockito.mock(ResponseBuilderUtil.class);

	@Bean
	public CouchBaseDAO<EProfile> couchBaseEProfileDao() {
		return dao;
	}

	@Bean
	public QPUMSClient QPUMSClientImpl() {
		return qpumsClient;
	}

	@Bean
	public MySubscriptionsInfoResponseBuilder mySubscriptionsInfoResponseBuilder() {
		return infoBuilder;
	}

	@Bean
	public MySubscriptionsCarouselResponseBuilder mySubscriptionsCarouselResponseBuilder() {
		return carouselBuilder;
	}

	@Bean
	public CouchbaseRepositoryFactory repository() {
		return Mockito.mock(CouchbaseRepositoryFactory.class);
	}

	@Bean
	public CouchbaseDatastore datastore() {
		return Mockito.mock(CouchbaseDatastore.class);
	}

}
