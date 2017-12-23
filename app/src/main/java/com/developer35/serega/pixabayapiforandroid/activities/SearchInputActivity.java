package com.developer35.serega.pixabayapiforandroid.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Spinner;

import com.developer35.serega.pixabayapiforandroid.R;

public class SearchInputActivity extends Activity {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    public static final String TAG_INPUT = "input";
    public static final String TAG_TYPE = "type";
    public static final String TAG_CATEGORY = "category";
    public static final String TAG_ORIENTATION = "orientation";
    private Spinner spinnerType;
    private Spinner spinnerCategory;
    private Spinner spinnerOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_input);
        checkForPermissions();

        spinnerType = findViewById(R.id.spinner_type);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerOrientation = findViewById(R.id.spinner_orientation);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setActivated(true);
        searchView.setOnQueryTextListener(queryTextListener);
    }

    private void checkForPermissions() {
        if (!isPermissionsGranted()) {
            ActivityCompat.requestPermissions(SearchInputActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    private boolean isPermissionsGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            }
        }
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
        intent.putExtra(TAG_TYPE, spinnerType.getSelectedItem().toString());
        intent.putExtra(TAG_CATEGORY, spinnerCategory.getSelectedItem().toString());
        intent.putExtra(TAG_ORIENTATION, spinnerOrientation.getSelectedItem().toString());
        startActivity(intent);
    }

}
