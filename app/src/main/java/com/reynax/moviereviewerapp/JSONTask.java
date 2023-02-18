package com.reynax.moviereviewerapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.reynax.moviereviewerapp.adapters.HorizontalMovieBoxAdapter;
import com.reynax.moviereviewerapp.data.Movie;
import com.reynax.moviereviewerapp.data.MoviesOutput;

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

public class JSONTask extends AsyncTask<String, Void, List<Movie>> {
    private final WeakReference<Fragment> fragmentReference;
    private final WeakReference<View> viewReference;

    public JSONTask(@NonNull Fragment fragment, @NonNull View view) {
        fragmentReference = new WeakReference<>(fragment);
        viewReference = new WeakReference<>(view);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);

        Fragment fragment = fragmentReference.get();
        View view = viewReference.get();
        if (fragment != null && view != null && movies != null) {
            LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
            manager.setOrientation(RecyclerView.HORIZONTAL);
            RecyclerView list = (RecyclerView) viewReference.get();
            list.setLayoutManager(manager);
            list.setAdapter(new HorizontalMovieBoxAdapter(fragment, movies));
            list.setNestedScrollingEnabled(false);
        }
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        InputStream is;
        List<Movie> movies = new ArrayList<>();
        try {
            URL url = new URL(strings[0] + "?api_key=" + Private.API_KEY + "&language=en-US&page=1");
            URLConnection request = url.openConnection();
            request.connect();

            is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            movies = gson.fromJson(gson.fromJson(jsonText, JsonObject.class).get("results"), listType);
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
}