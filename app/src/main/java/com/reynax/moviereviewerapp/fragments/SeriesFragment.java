package com.reynax.moviereviewerapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.adapters.SliderAdapter;

public class SeriesFragment extends Fragment {
    private ViewPager2 upcomingSlider;

    private final Handler upcomingSliderHandler = new Handler();

    private RecyclerView favoritesList;

    private RecyclerView newList;

    private RecyclerView topList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        favoritesList = view.findViewById(R.id.movies_fr_rv_favorites);
        newList = view.findViewById(R.id.movies_fr_rv_new);
        topList = view.findViewById(R.id.movies_fr_rv_top);
        upcomingSlider = view.findViewById(R.id.movies_fr_vp_releases);

        ((TextView)view.findViewById(R.id.movies_fr_tv_favorites_desc)).setText(getString(R.string.fr_series_favorites_desc));
        ((TextView)view.findViewById(R.id.movies_fr_tv_new)).setText(getString(R.string.fr_series_airing_now));
        ((TextView)view.findViewById(R.id.movies_fr_tv_theaters_desc)).setText(getString(R.string.fr_series_theaters_desc));
        ((TextView)view.findViewById(R.id.movies_fr_tv_top)).setText(getString(R.string.fr_series_top_all_string));

        JSONTask favoritesTask = new JSONTask(this, favoritesList, Globals.FRAGMENT_TYPE.SERIES);
        favoritesTask.execute("https://api.themoviedb.org/3/tv/popular");

        JSONTask latestTask = new JSONTask(this, newList, Globals.FRAGMENT_TYPE.SERIES);
        latestTask.execute("https://api.themoviedb.org/3/tv/on_the_air");

        JSONTask topTask = new JSONTask(this, topList, Globals.FRAGMENT_TYPE.SERIES);
        topTask.execute("https://api.themoviedb.org/3/tv/top_rated");

        JSONTask upcomingTask = new JSONTask(this, upcomingSlider, Globals.FRAGMENT_TYPE.SERIES);
        upcomingTask.execute("https://api.themoviedb.org/3/tv/airing_today");

        upcomingSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                SliderAdapter adapter = (SliderAdapter) upcomingSlider.getAdapter();
                if (adapter == null)
                    return;
                upcomingSlider.removeCallbacks(sliderIncrease);
                upcomingSlider.removeCallbacks(sliderLoop);
                if (position == adapter.getItemCount() - 1)
                    upcomingSlider.postDelayed(sliderLoop, Globals.SLIDE_DURATION);
                else upcomingSlider.postDelayed(sliderIncrease, Globals.SLIDE_DURATION);
            }
        });
        return view;
    }

    private final Runnable sliderIncrease = () ->
    {upcomingSlider.setCurrentItem(upcomingSlider.getCurrentItem() + 1, true);};

    private final Runnable sliderLoop = () -> {upcomingSlider.setCurrentItem(0, false);};
}
