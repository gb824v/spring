package com.att.eg.profile.mysubscriptions.info.model;

import com.att.ajsc.common.fis.model.ImageDominantColor;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelImageMetadata {

    @JsonProperty("imageId")
    private String imageId;

    @JsonProperty("imageType")
    private String imageType;

    @JsonProperty("width")
    private String width;

    @JsonProperty("height")
    private String height;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("dominantColor")
    private ImageDominantColor dominantColor;

    @JsonProperty("defaultImageUrl")
    private String defaultImageUrl;

    @JsonProperty("defaultDominantColor")
    private ImageDominantColor defaultDominantColor;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageDominantColor getDominantColor() {
        return dominantColor;
    }

    public void setDominantColor(ImageDominantColor dominantColor) {
        this.dominantColor = dominantColor;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    public ImageDominantColor getDefaultDominantColor() {
        return defaultDominantColor;
    }

    public void setDefaultDominantColor(ImageDominantColor defaultDominantColor) {
        this.defaultDominantColor = defaultDominantColor;
    }
}
