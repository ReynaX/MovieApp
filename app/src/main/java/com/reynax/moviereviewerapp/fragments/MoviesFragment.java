package com.reynax.moviereviewerapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;

public class MoviesFragment extends Fragment {

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

        JSONTask favoritesTask = new JSONTask(this, favoritesList);
        favoritesTask.execute("https://api.themoviedb.org/3/trending/movie/week");

        JSONTask latestTask = new JSONTask(this, newList);
        latestTask.execute("https://api.themoviedb.org/3/movie/now_playing");

        JSONTask topTask = new JSONTask(this, topList);
        topTask.execute("https://api.themoviedb.org/3/movie/top_rated");
        return view;
    }
}