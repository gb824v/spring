package com.att.eg.profile.mysubscriptions.info.model;

import java.util.ArrayList;
import java.util.List;

public class MySubscriptionsCarouselResponse {
    private List<Resource> resources = new ArrayList<>();

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "MySubscriptionsCarouselResponse{" +
                "resources=" + resources +
                '}';
    }
}
