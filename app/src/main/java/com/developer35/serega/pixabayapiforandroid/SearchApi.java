package com.developer35.serega.pixabayapiforandroid;


import com.developer35.serega.pixabayapiforandroid.entities.SearchEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("?key=7446566-3a89da866024fd9f5d4ae9572&per_page=200")
    Call<SearchEntity> getSearchResult(@Query("q") String query,
                                       @Query("image_type") String type,
                                       @Query("category") String category,
                                       @Query("orientation") String orientation);
}
