package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;

public class VerticalListActivity extends AppCompatActivity {

    private RecyclerView list;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_list);
        Bundle extras = getIntent().getExtras();

        list = findViewById(R.id.v_list_rv_main_view);
        title = findViewById(R.id.v_list_tv_title);
        title.setText(extras.getString("title"));

        if (this.getApplicationContext() != null) {
            JSONTask favoritesTask = new JSONTask(this.getApplicationContext(), list,
                    Globals.DATA_TYPE.MOVIES, Globals.ACTIVITY_TYPE.VERTICAL_LIST);
            favoritesTask.execute("https://api.themoviedb.org/3/trending/movie/week");
        }
    }


    public void onBackButtonPressed(View view) {
        onBackPressed();
    }
}