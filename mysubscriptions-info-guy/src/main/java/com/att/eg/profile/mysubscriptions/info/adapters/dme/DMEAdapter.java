package com.att.eg.profile.mysubscriptions.info.adapters.dme;

import com.att.aft.dme2.api.DME2Exception;
import com.att.eg.profile.mysubscriptions.info.model.DMEParams;
import com.att.eg.profile.mysubscriptions.info.model.DMEResponseInfo;

/**
 * Created by Annette.T on 9/10/2017.
 */
public interface DMEAdapter {

    DMEResponseInfo callServiceWithPost(DMEParams dmeParams) throws DME2Exception;

    DMEResponseInfo callServiceWithGet(DMEParams dmeParams) throws DME2Exception;

    DMEResponseInfo callServiceWithDelete(DMEParams dmeParams) throws DME2Exception;

    DMEResponseInfo callServiceWithPut(DMEParams dmeParams) throws DME2Exception;

}
