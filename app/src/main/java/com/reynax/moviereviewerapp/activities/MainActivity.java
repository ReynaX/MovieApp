package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.adapters.CategoryBoxAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout categoryTabLayout;
    private ViewPager2 categoryPager;
    private CategoryBoxAdapter categoryBoxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTabItems();

        categoryTabLayout = findViewById(R.id.main_tl_category);
        categoryPager = findViewById(R.id.main_vp_content);
        categoryBoxAdapter = new CategoryBoxAdapter(this);
        categoryPager.setAdapter(categoryBoxAdapter);


        categoryTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                categoryPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        categoryPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                categoryTabLayout.selectTab(categoryTabLayout.getTabAt(position));
            }
        });
        categoryPager.setUserInputEnabled(false);
    }

    private void createTabItems(){
        TabLayout mediaCategory = findViewById(R.id.main_tl_category);
        if(mediaCategory != null){
            mediaCategory.addTab(mediaCategory.newTab().setText("Movies"));
            mediaCategory.addTab(mediaCategory.newTab().setText("Streaming & TV"));
            mediaCategory.addTab(mediaCategory.newTab().setText("Celebrities"));
        }
    }

    public void onSeeAllButtonClicked(View view){
        Log.d("aha", String.valueOf(view.getId()));

        String title = null;
        if(view.getId() == R.id.movies_fr_btn_see_favorites)
            title = "Fan favorites";
        Intent intent = new Intent(this, VerticalListActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

}