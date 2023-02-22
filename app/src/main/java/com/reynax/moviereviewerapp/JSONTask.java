package com.reynax.moviereviewerapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.reynax.moviereviewerapp.activities.VerticalListActivity;
import com.reynax.moviereviewerapp.adapters.HorizontalContentBoxAdapter;
import com.reynax.moviereviewerapp.adapters.SliderAdapter;
import com.reynax.moviereviewerapp.adapters.VerticalContentBoxAdapter;
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.ContentDetailsListener;
import com.reynax.moviereviewerapp.data.Details;
import com.reynax.moviereviewerapp.data.Movie;
import com.reynax.moviereviewerapp.data.MovieDetails;
import com.reynax.moviereviewerapp.data.Series;
import com.reynax.moviereviewerapp.data.SeriesDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JSONTask extends AsyncTask<String, Void, List<Content>> {
    private final WeakReference<View> viewReference;

    private final Globals.DATA_TYPE dataType;
    private final Globals.ACTIVITY_TYPE activityType;

    public JSONTask(@NonNull View view, Globals.DATA_TYPE dataType, Globals.ACTIVITY_TYPE activityType) {
        this.viewReference = new WeakReference<>(view);
        this.dataType = dataType;
        this.activityType = activityType;
    }

    @Override
    protected void onPostExecute(List<Content> movies) {
        super.onPostExecute(movies);

        switch (activityType) {
            case MAIN:
                onPostExecuteMainActivity(movies);
                break;
            case VERTICAL_LIST:
                onPostExecuteVerticalListActivity(movies);
                break;
        }
    }

    @Override
    protected List<Content> doInBackground(String... strings) {

        List<Content> movies = new ArrayList<>();
        try {
            URL url = new URL(strings[0] + "?api_key=" + Private.API_KEY + "&language=en-US&page=1");
            URLConnection request = url.openConnection();
            request.connect();

            try (
                    InputStream is = url.openStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
            ) {
                String jsonText = Globals.readAll(rd);
                Gson gson = new Gson();
                Type listType;
                switch (dataType) {
                    case MOVIES:
                        listType = new TypeToken<ArrayList<Movie>>() {
                        }.getType();
                        break;
                    case SERIES:
                        listType = new TypeToken<ArrayList<Series>>() {
                        }.getType();
                        break;
                    default:
                        return movies;
                }
                movies = gson.fromJson(gson.fromJson(jsonText, JsonObject.class).get("results"), listType);
                Globals.loadDetails(movies, dataType);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    private void onPostExecuteMainActivity(List<Content> movies) {
        View view = viewReference.get();
        if (view != null && movies != null) {
            if (view instanceof RecyclerView) {
                LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(RecyclerView.HORIZONTAL);
                RecyclerView list = (RecyclerView) view;
                list.setLayoutManager(manager);
                list.setAdapter(new HorizontalContentBoxAdapter(movies, item ->
                        new ContentDetailsListener(view.getContext()).onItemClick(item)));
                list.setNestedScrollingEnabled(false);
            } else if (view instanceof ViewPager2) {
                ViewPager2 slider = (ViewPager2) view;
                slider.setAdapter(new SliderAdapter(movies, item ->
                        new ContentDetailsListener(view.getContext()).onItemClick(item)));
            }
        }
    }

    private void onPostExecuteVerticalListActivity(List<Content> movies) {
        View view = viewReference.get();
        if (view != null && movies != null) {
            LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            RecyclerView list = (RecyclerView) view;
            list.setLayoutManager(manager);
            list.setAdapter(new VerticalContentBoxAdapter(movies,
                    item -> new ContentDetailsListener(view.getContext()).onItemClick(item)));
            list.setNestedScrollingEnabled(false);
        }
    }
}
