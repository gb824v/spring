package com.att.eg.profile.mysubscriptions.info.model;

import com.att.ajsc.common.fis.model.FISImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelAsset {

    @JsonProperty("resourceId")
    private String resourceId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("imageList")
    private List<FISImage> imageList;

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FISImage> getImageList() {
        return this.imageList;
    }

    public void setImageList(List<FISImage> imageList) {
        this.imageList = imageList;
    }

}
