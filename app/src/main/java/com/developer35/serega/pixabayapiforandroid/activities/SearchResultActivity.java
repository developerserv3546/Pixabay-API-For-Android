package com.developer35.serega.pixabayapiforandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.developer35.serega.pixabayapiforandroid.R;
import com.developer35.serega.pixabayapiforandroid.SearchApi;
import com.developer35.serega.pixabayapiforandroid.SearchService;
import com.developer35.serega.pixabayapiforandroid.adapters.ItemAdapter;
import com.developer35.serega.pixabayapiforandroid.entities.ItemEntity;
import com.developer35.serega.pixabayapiforandroid.entities.SearchEntity;
import com.developer35.serega.pixabayapiforandroid.interfaces.AdapterClickListener;
import com.developer35.serega.pixabayapiforandroid.utils.StringConverter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends Activity {

    private RecyclerView recyclerView;
    private ArrayList<ItemEntity> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String input = getIntent().getStringExtra(SearchInputActivity.TAG_INPUT);
        String query = StringConverter.getQueryString(input);

        SearchApi searchApi = SearchService.getSearchApi();
        Call<SearchEntity> searchEntities = searchApi.getSearchResult(query);
        searchEntities.enqueue(callback);
    }

    private final Callback<SearchEntity> callback = new Callback<SearchEntity>() {
        @Override
        public void onResponse(Call<SearchEntity> call, Response<SearchEntity> response) {
            if (response.isSuccessful()) {
                SearchEntity result = response.body();
                if (result != null) {
                    items = result.getItems();
                    ItemAdapter adapter = new ItemAdapter(items, adapterClickListener);
                    recyclerView.setAdapter(adapter);
                } else {
                    showErrorToast();
                }
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

    private final AdapterClickListener adapterClickListener = new AdapterClickListener() {
        @Override
        public void onClick(int position, View view) {
            switch (view.getId()) {
                case R.id.thumbnail_view:
                    openPageInBrowser(position);
                    break;
                case R.id.action_download:
                    break;
            }
        }
    };

    private void openPageInBrowser(int position) {
        String url = items.get(position).getPageURL();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        List<ResolveInfo> activities = getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (activities != null && activities.size() != 0) {
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.no_applications), Toast.LENGTH_SHORT).show();
        }

    }

    private void showErrorToast() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
