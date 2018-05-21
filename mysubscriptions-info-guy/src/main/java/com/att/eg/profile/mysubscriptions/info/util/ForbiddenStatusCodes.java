package com.att.eg.profile.mysubscriptions.info.util;

/**
 * Created by Annette.T on 10/3/2017.
 */
public enum ForbiddenStatusCodes {

    NOT_AUTHORIZED_USER("000"),
    NOT_AUTHORIZED_FOR_CONTENT("001"),
    CONSUMABLE_NOT_RECORDABLE("003"),
    DISCOVERY_MISSING_DATA("006");


    private String statusAsStr;

    ForbiddenStatusCodes(String statusAsStr) {
        this.statusAsStr = statusAsStr;
    }

    public String getStatusAsStr() {
        return statusAsStr;
    }

}
