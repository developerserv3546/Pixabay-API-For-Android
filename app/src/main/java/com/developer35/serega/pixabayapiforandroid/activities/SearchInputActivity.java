package com.developer35.serega.pixabayapiforandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SearchView;

import com.developer35.serega.pixabayapiforandroid.R;

public class SearchInputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_input);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setActivated(true);
        searchView.setOnQueryTextListener(queryTextListener);
    }

    private final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };


}
