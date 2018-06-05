package com.att.eg.profile.mysubscriptions.info.adapters;

import com.att.ajsc.common.couchbase.repository.impl.CouchBaseDAO;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.model.EProfile;
import com.att.eg.profile.mysubscriptions.info.model.Status;
import com.att.eg.profile.mysubscriptions.info.service.SubscriptionComparisonServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EProfileAdapterImpl implements EProfileAdapter {

    private final CouchBaseDAO<EProfile> couchBaseDAO;
    private final YawlLogger log = new YawlLogger(SubscriptionComparisonServiceImpl.class);

    public EProfileAdapterImpl(@Autowired CouchBaseDAO<EProfile> couchBaseDAO) {
        this.couchBaseDAO = couchBaseDAO;
    }

    @Override
    public EProfile getById(String id) {

        EProfile eProfile = couchBaseDAO.findById(id);
        if (eProfile == null) {
            log.info(Status.ERROR_GENERIC,
                    new MetaBuilder()
                            .setReason("No record exists for the given Profile Id")
                            .create());

            return null;
        }

        return eProfile;
    }
}
