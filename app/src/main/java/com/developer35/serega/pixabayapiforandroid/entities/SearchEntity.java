package com.developer35.serega.pixabayapiforandroid.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchEntity {

    @SerializedName("total")
    @Expose
    private Long total;

    @SerializedName("totalHits")
    @Expose
    private Long totalHits;

    @SerializedName("hits")
    @Expose
    private List<ItemEntity> imageEntities = new ArrayList<>();

    public Long getTotal() {
        return total;
    }

    public Long getTotalHits() {
        return totalHits;
    }

    public List<ItemEntity> getImageEntities() {
        return imageEntities;
    }

    @Override
    public String toString() {
        return "SearchEntity{" +
                "total=" + total +
                ", totalHits=" + totalHits +
                ", imageEntities=" + imageEntities +
                '}';
    }
}
