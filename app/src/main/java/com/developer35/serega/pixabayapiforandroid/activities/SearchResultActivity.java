package com.developer35.serega.pixabayapiforandroid.activities;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.developer35.serega.pixabayapiforandroid.Fabric;
import com.developer35.serega.pixabayapiforandroid.R;
import com.developer35.serega.pixabayapiforandroid.SearchApi;
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

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemEntity> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String query = getIntent().getStringExtra(SearchInputActivity.TAG_INPUT);
        query = StringConverter.getQueryString(query);

        String type = getIntent().getStringExtra(SearchInputActivity.TAG_TYPE);
        type = StringConverter.getImageTypeQuery(type);

        String category = getIntent().getStringExtra(SearchInputActivity.TAG_CATEGORY);
        category = StringConverter.getImageCategoryQuery(category);

        String orientation = getIntent().getStringExtra(SearchInputActivity.TAG_ORIENTATION);
        orientation = StringConverter.getImageOrientationQuery(orientation);

        SearchApi searchApi = Fabric.getSearchApi();
        Call<SearchEntity> searchEntities = searchApi
                .getSearchResult(query, type, category, orientation);
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
                    downloadImage(position);
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

    private void downloadImage(int position) {
        String url = items.get(position).getWebformatURL();
        String name = StringConverter.getImageNameFromUrl(url);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);

        if (downloadManager != null) {
            downloadManager.enqueue(request);
        } else {
            showErrorToast();
        }
    }

    private void showErrorToast() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
