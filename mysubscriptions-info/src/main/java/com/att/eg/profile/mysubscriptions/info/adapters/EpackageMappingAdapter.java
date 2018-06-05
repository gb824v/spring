package com.att.eg.profile.mysubscriptions.info.adapters;

import com.att.eg.profile.mysubscriptions.info.model.EpackageMappingResponse;

@FunctionalInterface
public interface EpackageMappingAdapter {
    EpackageMappingResponse getPackage(String packageCode);
}
