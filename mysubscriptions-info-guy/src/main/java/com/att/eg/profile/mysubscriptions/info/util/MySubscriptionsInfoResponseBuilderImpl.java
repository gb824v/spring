package com.att.eg.profile.mysubscriptions.info.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.att.ajsc.common.couchbase.repository.impl.CouchBaseDAO;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.AddOn;
import com.att.eg.profile.mysubscriptions.info.model.AddOnsInfo;
import com.att.eg.profile.mysubscriptions.info.model.BasePackageInfo;
import com.att.eg.profile.mysubscriptions.info.model.CDvrInfo;
import com.att.eg.profile.mysubscriptions.info.model.ColorCode;
import com.att.eg.profile.mysubscriptions.info.model.EProfile;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.model.Product;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.model.Subscription;
import com.att.eg.profile.mysubscriptions.info.model.SubscriptionData;
import com.att.eg.profile.mysubscriptions.info.service.QPUMSClient;
import org.springframework.beans.factory.annotation.Autowired;

public class MySubscriptionsInfoResponseBuilderImpl implements MySubscriptionsInfoResponseBuilder{

    private final YawlLogger log = new YawlLogger(MySubscriptionsInfoResponseBuilderImpl.class);

	private final CouchBaseDAO<EProfile> couchBaseDAO;
	private final ColorCodeBuilder colorCodeBuilder;
	private final QPUMSClient qpumsClient;
    private final ResponseBuilderUtil responseBuilderUtil;

    @Autowired
	public MySubscriptionsInfoResponseBuilderImpl(CouchBaseDAO<EProfile> couchBaseDAO,
                                                  ColorCodeBuilder colorCodeBuilder,
                                                  QPUMSClient qpumsClient,
                                                  ResponseBuilderUtil responseBuilderUtil){
		// needed for autowiring
		this.couchBaseDAO = couchBaseDAO;
		this.colorCodeBuilder = colorCodeBuilder;
		this.qpumsClient = qpumsClient;
		this.responseBuilderUtil = responseBuilderUtil;
	}
	
	@Override
	public MySubscriptionsInfoResponse buildResponse(String profileId, String secureToken) {

	    MySubscriptionsInfoResponse mySubscriptionsInfoResponse;
		if(secureToken !=null) {
		    log.info(Status.OK, "Generating Response Using SecureId");
            mySubscriptionsInfoResponse = generateSubscriptionInfoResponseFromSecureToken(secureToken);
		} else {
            log.info(Status.OK, "Generating Response Using ProfileId");
			EProfile eProfile = couchBaseDAO.findById(profileId);
			if(eProfile == null) {
                log.info(Status.ERROR_GENERIC,
                        new MetaBuilder()
                                .setReason("No record exists for the given Profile Id")
                                .create());

                return null;
			}
            mySubscriptionsInfoResponse = generateSubscriptionInfoResponseFromProfileId(eProfile);
		}
        return mySubscriptionsInfoResponse;
	}

	private MySubscriptionsInfoResponse generateSubscriptionInfoResponseFromSecureToken(String secureToken) {

        MySubscriptionsInfoResponse response = null;

		String sessionId = responseBuilderUtil.getQPSessionId(secureToken);
		if(sessionId != null) {
			ActiveSubscriptionsResponse activeSubscriptionResponse = qpumsClient.getActiveSubscriptions(sessionId);
			if(activeSubscriptionResponse != null) {
                Map<String, Product> skuProductMap = responseBuilderUtil.getSkuProductMap();
                response = populateResponse(activeSubscriptionResponse, skuProductMap);
            } else {
			    responseBuilderUtil.invalidateSessionCacheFor(secureToken);
            }
		} else {
            responseBuilderUtil.throwUnauthorizedException(this.getClass().getName());
        }

        return response;
    }

    private MySubscriptionsInfoResponse populateResponse(ActiveSubscriptionsResponse activeSubscriptionResponse, Map<String, Product> skuProductMap) {

	    BasePackageInfo basePackageInfo = new BasePackageInfo();
        AddOnsInfo addOnsInfo = new AddOnsInfo();
        CDvrInfo cDvrInfo = new CDvrInfo();

	    SubscriptionData subscriptionData = activeSubscriptionResponse.getData();
        String nextBillingAmount = subscriptionData.getNextBillingAmount();
        List<Subscription> subscriptions = subscriptionData.getSubscriptions();
        List<AddOn> addOns = new ArrayList<>();
        float totalAddOnsPrice = 0;
        for(Subscription subscription : subscriptions) {
            String sku = subscription.getSku();
            Product product = skuProductMap.get(sku);
            if (product.isBasicService()) {
                basePackageInfo.setDisplayName(product.getDisplayName());
                basePackageInfo.setPriceUsd(subscription.getRetailPrice());
                String channelCount = responseBuilderUtil.parseChannelCount(product.getDescription());
                basePackageInfo.setChannelCount(channelCount);
            } else {
                String category = product.getCategory();
                switch (category) {
                    case "Subscription Channels":
						AddOn addOn = buildAddOn(subscription, product);
                        totalAddOnsPrice += Float.parseFloat(addOn.getPriceUsd());
                        addOns.add(addOn);
                        break;
                    case "DVR":
                        cDvrInfo.setAvailableHours(product.getStorageCapacity() + " Hours");
                        cDvrInfo.setPriceUsd(subscription.getRetailPrice());
                        break;
                    default:
                        log.info(Status.ERROR_GENERIC,
                                 new MetaBuilder()
                                 .setReason("Looks like In-App Purchases feature is starting to show new Categories than the Above - Time to update the code")
                                 .create());
                        break;
                }
            }
        }
        addOnsInfo.setAddOns(addOns.toArray(new AddOn[0]));
        addOnsInfo.setAddOnsCount(addOns.size() + " Packages");
        addOnsInfo.setTotalPriceUsd(Float.toString(totalAddOnsPrice));
        //get color code based on base package name
        ColorCode colorCode = colorCodeBuilder.buildColorCode(basePackageInfo.getDisplayName());
        basePackageInfo.setColorCode(colorCode);

        return new MySubscriptionsInfoResponse(basePackageInfo, addOnsInfo, cDvrInfo, nextBillingAmount);

    }

	private AddOn buildAddOn(Subscription subscription, Product product) {
		AddOn addOn = new AddOn();
		addOn.setDisplayName(product.getDisplayName());
		addOn.setPriceUsd(subscription.getRetailPrice());
		return addOn;
	}

	private MySubscriptionsInfoResponse generateSubscriptionInfoResponseFromProfileId(EProfile eProfile) {

		BasePackageInfo basePackageInfo = new BasePackageInfo();
		AddOnsInfo addOnsInfo = new AddOnsInfo();
		CDvrInfo cDvrInfo = new CDvrInfo();

		//sample packageCode: "GO BIG + HBO + Cinamax + DVR 100 HOURS"
		String packageCode = eProfile.getPackageCode();
		String[] splitPackages = packageCode.split(" \\+ ");
		//name of user's base package
		basePackageInfo.setDisplayName(splitPackages[0]);
		//Check if they bought more dvr hours, if not default is 10
		//it's the last item in string and looks like "DVR 100 HOURS"
		int responseLength = splitPackages.length;
		if (splitPackages[responseLength - 1].contains("DVR")) {
			String hours = splitPackages[responseLength - 1].replace("DVR ", "").replace("HOURS", "Hours");
			cDvrInfo.setAvailableHours(hours);
			--responseLength;
		} else {
			cDvrInfo.setAvailableHours("10 Hours");
		}
		//count number of addon channels excluding dvr hours and base package
		int addOnsCount = responseLength - 1;
		addOnsInfo.setAddOnsCount(addOnsCount + " Packages");
		//populate AddOns
		AddOn[] addOns = new AddOn[addOnsCount];
		for (int i = 0; i < addOnsCount; ++i) {
			AddOn addOn = new AddOn();
			addOn.setDisplayName(splitPackages[i + 1]);
			addOns[i] = addOn;
		}
		addOnsInfo.setAddOns(addOns);
		//get color code based on base package name
		ColorCode colorCode = colorCodeBuilder.buildColorCode(basePackageInfo.getDisplayName());
		basePackageInfo.setColorCode(colorCode);
		return new MySubscriptionsInfoResponse(basePackageInfo, addOnsInfo, cDvrInfo, null);
	}

}
