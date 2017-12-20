package com.developer35.serega.pixabayapiforandroid;


import com.developer35.serega.pixabayapiforandroid.entities.SearchEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchApi {

    @GET("?key=7446566-3a89da866024fd9f5d4ae9572&q=yellow+flowers&image_type=photo")
    Call<SearchEntity> getSearchResult();
}
