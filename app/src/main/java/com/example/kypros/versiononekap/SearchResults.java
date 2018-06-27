package com.example.kypros.versiononekap;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

public class SearchResults extends Activity {

    SearchView searchBar;
    SwipeRefreshLayout mySwipeRefreshLayoutSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        SearchView searchBar= (SearchView) findViewById(R.id.searchBar);

        searchBar.setQueryHint("Search your service...");



        //DRAG DOWN TO REFRESH LAYOUT STARTS--------------------------------------------------------
        mySwipeRefreshLayoutSearch = (SwipeRefreshLayout)this.findViewById(R.id.swipe_layout_search);

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
