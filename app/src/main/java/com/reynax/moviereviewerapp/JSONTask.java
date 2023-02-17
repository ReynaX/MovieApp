package com.reynax.moviereviewerapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.reynax.moviereviewerapp.data.Movie;
import com.reynax.moviereviewerapp.data.MoviesOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void unused) {

        super.onPostExecute(unused);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key=992cb140874f2674a2f63ff5b178faf8&language=en-US&page=1");
            URLConnection request = url.openConnection();
            request.connect();

            is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);

            Gson gson = new Gson();

            MoviesOutput output = gson.fromJson(jsonText, MoviesOutput.class);
            Type listType = new TypeToken<ArrayList<Movie>>(){}.getType();
            List<Movie> movies = gson.fromJson(gson.fromJson(jsonText, JsonObject.class).get("results"), listType);

            Log.e("a", movies.toString());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
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
