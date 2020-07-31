package com.mefy.cavista_demo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mefy.cavista_demo.R;
import com.mefy.cavista_demo.controller.BaseActivity;
import com.mefy.cavista_demo.controller.networking.ApiHandler;
import com.mefy.cavista_demo.model.bean.Data;
import com.mefy.cavista_demo.model.bean.Images;
import com.mefy.cavista_demo.model.responces.CommonResponse;
import com.mefy.cavista_demo.utils.Finals;
import com.mefy.cavista_demo.utils.InternetHelper;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private static String TAG = "MainActivity";
    private GridView gridView;
    private ArrayList<Images> images = new ArrayList<>();
    private Adapter adapter;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initData() {
        manageViewVisibility(false, getResources().getString(R.string.Search));
        SetAdapter();
    }

    private void initViews() {
        gridView = findViewById(R.id.gridView);
        tvEmpty = findViewById(R.id.empty);
    }

    private void makeAPICall(String queryParam) {
        new ApiHandler(MainActivity.this, Finals.SEARCH, queryParam);
    }

    private void SetAdapter() {
        adapter = new Adapter(this, images);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onGetResponse(CommonResponse response, String callFor) {
        images.clear();
        if(callFor.equals(Finals.SEARCH) && response!= null && response.isSuccess() && response.getStatus() == 200 && !response.getData().isEmpty()){
            for(Data data : response.getData()){
                images.addAll(data.getImages());
            }
            manageViewVisibility(true, "");
        }else{
            manageViewVisibility(false, getResources().getString(R.string.opps));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                manageViewVisibility(true, "");
                if (InternetHelper.isConnectingToInternet(MainActivity.this)) {
                    makeAPICall(s);
                }else {
                    MainActivity.this.customToastActivity(MainActivity.this, getResources().getString(R.string.no_internet));
                }
                searchView.setIconified(true);
                searchView.clearFocus();
                // call collapse action view on 'MenuItem'
                (menu.findItem(R.id.action_search)).collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange ");
                return false;
            }
        });
        searchView.setIconified(false);
        return true;
    }

    private void manageViewVisibility(boolean isGridVisible, String message) {
        if(isGridVisible){
            tvEmpty.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }else{
            tvEmpty.setText(message);
            tvEmpty.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }
    }
}