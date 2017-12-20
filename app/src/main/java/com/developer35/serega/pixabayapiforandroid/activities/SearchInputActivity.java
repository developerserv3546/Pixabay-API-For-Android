package com.developer35.serega.pixabayapiforandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.developer35.serega.pixabayapiforandroid.R;

public class SearchInputActivity extends Activity {

    public static final String TAG_INPUT = "input";

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
            startSearchResultActivity(s);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };

    private void startSearchResultActivity(String input) {
        Intent intent = new Intent(SearchInputActivity.this, SearchResultActivity.class);
        intent.putExtra(TAG_INPUT, input);
        startActivity(intent);
    }


}
