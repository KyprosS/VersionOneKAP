package com.example.kypros.versiononekap;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridView;

public class ChildCategoriesDynamicActivity extends BaseActivity {

    SwipeRefreshLayout mySwipeRefreshLayoutSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String parent_id = getIntent().getStringExtra("PARENT_ID");

        System.out.print(parent_id);


        //Grid View declaration
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));



        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_child_categories_dynamic, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------


        //DRAG DOWN TO REFRESH LAYOUT STARTS--------------------------------------------------------
        mySwipeRefreshLayoutSearch = (SwipeRefreshLayout) this.findViewById(R.id.swipeContainer);

        mySwipeRefreshLayoutSearch.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        //DRAG DOWN TO REFRESH LAYOUT ENDS----------------------------------------------------------
    }
}
