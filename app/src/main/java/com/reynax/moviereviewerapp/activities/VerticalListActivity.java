package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.adapters.VerticalContentBoxAdapter;
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.Details;
import com.reynax.moviereviewerapp.data.MovieDetails;
import com.reynax.moviereviewerapp.data.SeriesDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VerticalListActivity extends AppCompatActivity {

    private RecyclerView list;
    private TextView title;
    private SearchView searchView;
    private Button loadMoreButton;

    private Globals.DATA_TYPE dataType;
    private String query;

    private List<Content> items = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_list);
        Bundle extras = getIntent().getExtras();

        list = findViewById(R.id.v_list_rv_main_view);
        title = findViewById(R.id.v_list_tv_title);
        searchView = findViewById(R.id.v_list_sv_search);
        loadMoreButton = findViewById(R.id.v_list_btn_load_more);

        title.setText(extras.getString("title"));
        this.query = (String) extras.get("query");
        this.dataType = (Globals.DATA_TYPE) extras.get("dataType");

        if (this.getApplicationContext() != null) {
            JSONTask task = new JSONTask(list, dataType, Globals.ACTIVITY_TYPE.VERTICAL_LIST);
            task.execute("https://api.themoviedb.org/3/" + query);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });

        searchView.setOnSearchClickListener(view -> {
            VerticalContentBoxAdapter adapter = (VerticalContentBoxAdapter) list.getAdapter();
            if(adapter != null){
                items = adapter.getItems();
            }
        });
        hideNavigationBar();
    }


    public void onBackButtonPressed(View view) {
        onBackPressed();
    }

    public void onLoadMoreButtonPressed(View view){
        String q = "https://api.themoviedb.org/3/" + this.query;

        VerticalContentBoxAdapter adapter = (VerticalContentBoxAdapter) list.getAdapter();
        if(adapter != null){
            adapter.loadMore(q, getApplicationContext(), loadMoreButton);
            if(!searchView.isIconified())
                searchView.setIconified(true);
        }
    }

    private void hideNavigationBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void filter(String text){
        VerticalContentBoxAdapter adapter = (VerticalContentBoxAdapter) list.getAdapter();
        if(adapter != null && items != null) {
            ArrayList<Content> filteredList = new ArrayList<>();

            for (Content item : items) {
                if (item.getTitle().toLowerCase(Locale.ROOT).contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }

            adapter.filter(filteredList);
        }
    }
}