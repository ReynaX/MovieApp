package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.adapters.HorizontalContentBoxAdapter;

import java.util.ArrayList;

public class VerticalListActivity extends AppCompatActivity {

    private RecyclerView list;

    private HorizontalContentBoxAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_list);

        list = findViewById(R.id.v_list_rv_main_view);
        list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listAdapter = new HorizontalContentBoxAdapter(getApplication(), new ArrayList<>());
        list.setAdapter(listAdapter);
    }


    public void onBackButtonPressed(View view){
        onBackPressed();
    }
}