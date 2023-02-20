package com.reynax.moviereviewerapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.reynax.moviereviewerapp.adapters.HorizontalContentBoxAdapter;
import com.reynax.moviereviewerapp.adapters.SliderAdapter;
import com.reynax.moviereviewerapp.adapters.VerticalContentBoxAdapter;
import com.reynax.moviereviewerapp.data.Content;
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
    private final WeakReference<Context> contextReference;
    private final WeakReference<View> viewReference;

    private final Globals.DATA_TYPE dataType;
    private final Globals.ACTIVITY_TYPE activityType;

    public JSONTask(@NonNull Context context, @NonNull View view, Globals.DATA_TYPE dataType, Globals.ACTIVITY_TYPE activityType) {
        this.contextReference = new WeakReference<>(context);
        this.viewReference = new WeakReference<>(view);
        this.dataType = dataType;
        this.activityType = activityType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
                String jsonText = readAll(rd);
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
                loadDetails(movies);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private void onPostExecuteMainActivity(List<Content> movies) {
        View view = viewReference.get();
        Context context = contextReference.get();
        if (context != null && view != null && movies != null) {
            if (view instanceof RecyclerView) {
                LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(RecyclerView.HORIZONTAL);
                RecyclerView list = (RecyclerView) view;
                list.setLayoutManager(manager);
                list.setAdapter(new HorizontalContentBoxAdapter(context, movies));
                list.setNestedScrollingEnabled(false);
            } else if (view instanceof ViewPager2) {
                ViewPager2 slider = (ViewPager2) view;
                slider.setAdapter(new SliderAdapter(context, movies));
            }
        }
    }

    private void onPostExecuteVerticalListActivity(List<Content> movies) {
        View view = viewReference.get();
        Context context = contextReference.get();
        if (context != null && view != null && movies != null) {
            LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            RecyclerView list = (RecyclerView) view;
            list.setLayoutManager(manager);
            list.setAdapter(new VerticalContentBoxAdapter(context, movies));
            list.setNestedScrollingEnabled(false);
        }
    }

    private void loadDetails(List<Content> items) {
        String contentType = "";
        Class<?> contentClass = Details.class;
        switch (dataType) {
            case MOVIES:
                contentType = "movie";
                contentClass = MovieDetails.class;
                break;
            case SERIES:
                contentType = "tv";
                contentClass = SeriesDetails.class;
                break;
        }

        for (Content item : items) {
            String urlString = String.format(Locale.ENGLISH,
                    "https://api.themoviedb.org/3/%s/%d?api_key=%s&language=en-US",
                    contentType, item.getId(), Private.API_KEY);
            try {
                URL url = new URL(urlString);
                URLConnection request = url.openConnection();
                request.connect();

                try (
                        InputStream is = url.openStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                ) {
                    String jsonText = readAll(rd);
                    item.setDetails(new Gson().fromJson(jsonText, (Type) contentClass));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
