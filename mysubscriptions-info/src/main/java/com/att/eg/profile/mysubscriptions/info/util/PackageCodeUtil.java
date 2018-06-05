package com.att.eg.profile.mysubscriptions.info.util;

public final class PackageCodeUtil {
    private PackageCodeUtil() {

    }

    public static String convertFromSkuToPackageCode(String sku) {
        return sku.split("-")[1];
    }
}
