package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.reynax.moviereviewerapp.Globals;
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
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        categoryPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                categoryTabLayout.selectTab(categoryTabLayout.getTabAt(position));
            }
        });
        categoryPager.setUserInputEnabled(false);
        hideNavigationBar();
    }

    private void createTabItems() {
        TabLayout mediaCategory = findViewById(R.id.main_tl_category);
        if (mediaCategory != null) {
            mediaCategory.addTab(mediaCategory.newTab().setText("Movies"));
            mediaCategory.addTab(mediaCategory.newTab().setText("Streaming & TV"));
            mediaCategory.addTab(mediaCategory.newTab().setText("Celebrities"));
        }
    }

    public void onSeeAllButtonClicked(View view) {
        String title = null;
        int id = view.getId();
        String query = null;
        Globals.DATA_TYPE dataType = categoryPager.getCurrentItem() == 0 ? Globals.DATA_TYPE.MOVIES : Globals.DATA_TYPE.SERIES;
        if (id == R.id.movies_fr_btn_see_favorites) {
            query = categoryPager.getCurrentItem() == 0 ? "trending/movie/week" : "tv/popular";
            title = "Fan favorites";
        } else if (id == R.id.movies_fr_btn_see_theaters) {
            query = categoryPager.getCurrentItem() == 0 ? "movie/now_playing" : "tv/on_the_air";
            title = "New releases";
        } else return;

        Intent intent = new Intent(this, VerticalListActivity.class);
        intent.putExtra("query", query);
        intent.putExtra("title", title);
        intent.putExtra("dataType", dataType);
        startActivity(intent);
    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}