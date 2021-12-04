package com.osmtech.dubizzleassesment.classifiedsapp.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassifiedModel {

    @SerializedName("results")
    private List<ResultsDTO> results;
    @SerializedName("pagination")
    private PaginationDTO pagination;

    public List<ResultsDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultsDTO> results) {
        this.results = results;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public static class PaginationDTO {
        @SerializedName("key")
        private Object key;

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }
    }

    public static class ResultsDTO {
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("price")
        private String price;
        @SerializedName("name")
        private String name;
        @SerializedName("uid")
        private String uid;
        private Bitmap imageData;
        @SerializedName("image_ids")
        private List<String> imageIds;
        @SerializedName("image_urls")
        private List<String> imageUrls;
        @SerializedName("image_urls_thumbnails")
        private List<String> imageUrlsThumbnails;

        public Bitmap getImageData() {
            return imageData;
        }

        public void setImageData(Bitmap imageData) {
            this.imageData = imageData;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public List<String> getImageIds() {
            return imageIds;
        }

        public void setImageIds(List<String> imageIds) {
            this.imageIds = imageIds;
        }

        public List<String> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public List<String> getImageUrlsThumbnails() {
            return imageUrlsThumbnails;
        }

        public void setImageUrlsThumbnails(List<String> imageUrlsThumbnails) {
            this.imageUrlsThumbnails = imageUrlsThumbnails;
        }
    }
}
