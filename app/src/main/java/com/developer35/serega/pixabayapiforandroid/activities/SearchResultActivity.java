package com.developer35.serega.pixabayapiforandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.developer35.serega.pixabayapiforandroid.R;
import com.developer35.serega.pixabayapiforandroid.SearchApi;
import com.developer35.serega.pixabayapiforandroid.SearchService;
import com.developer35.serega.pixabayapiforandroid.entities.ItemEntity;
import com.developer35.serega.pixabayapiforandroid.entities.SearchEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        SearchApi searchApi = SearchService.getSearchApi();
        Call<SearchEntity> searchEntities = searchApi.getSearchResult();
        searchEntities.enqueue(callback);
    }

    private final Callback<SearchEntity> callback = new Callback<SearchEntity>() {
        @Override
        public void onResponse(Call<SearchEntity> call, Response<SearchEntity> response) {
            if (response.isSuccessful()) {
                SearchEntity result = response.body();
                List<ItemEntity> entities = result.getImageEntities();
            } else {
                showErrorToast();
            }
        }

        @Override
        public void onFailure(Call<SearchEntity> call, Throwable t) {
            t.printStackTrace();
            showErrorToast();
        }
    };

    private void showErrorToast() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
