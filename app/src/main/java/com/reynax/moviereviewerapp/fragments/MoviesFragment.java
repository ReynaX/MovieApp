package com.reynax.moviereviewerapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.reynax.moviereviewerapp.R;

public class MoviesFragment extends Fragment {

    private RecyclerView favoritesList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        favoritesList = view.findViewById(R.id.movies_fr_rv_favorites);

        return view;
    }
}
