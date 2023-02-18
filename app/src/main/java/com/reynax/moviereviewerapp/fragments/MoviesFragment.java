package com.reynax.moviereviewerapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.adapters.SliderAdapter;

public class MoviesFragment extends Fragment {
    private ViewPager2 upcomingSlider;

    private final Handler upcomingSliderHandler = new Handler();

    private RecyclerView favoritesList;

    private RecyclerView newList;

    private RecyclerView topList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        favoritesList = view.findViewById(R.id.movies_fr_rv_favorites);
        newList = view.findViewById(R.id.movies_fr_rv_new);
        topList = view.findViewById(R.id.movies_fr_rv_top);
        upcomingSlider = view.findViewById(R.id.movies_fr_vp_releases);

        JSONTask favoritesTask = new JSONTask(this, favoritesList);
        favoritesTask.execute("https://api.themoviedb.org/3/trending/movie/week");

        JSONTask latestTask = new JSONTask(this, newList);
        latestTask.execute("https://api.themoviedb.org/3/movie/now_playing");

        JSONTask topTask = new JSONTask(this, topList);
        topTask.execute("https://api.themoviedb.org/3/movie/top_rated");

        JSONTask upcomingTask = new JSONTask(this, upcomingSlider);
        upcomingTask.execute("https://api.themoviedb.org/3/movie/upcoming");

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
