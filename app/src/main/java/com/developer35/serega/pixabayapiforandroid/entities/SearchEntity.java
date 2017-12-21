package com.developer35.serega.pixabayapiforandroid.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchEntity {

    @SerializedName("total")
    @Expose
    private Long total;

    @SerializedName("totalHits")
    @Expose
    private Long totalHits;

    @SerializedName("hits")
    @Expose
    private ArrayList<ItemEntity> items = new ArrayList<>();

    public Long getTotal() {
        return total;
    }

    public Long getTotalHits() {
        return totalHits;
    }

    public ArrayList<ItemEntity> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SearchEntity{" +
                "total=" + total +
                ", totalHits=" + totalHits +
                ", items=" + items +
                '}';
    }
}
