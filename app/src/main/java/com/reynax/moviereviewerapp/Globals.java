package com.reynax.moviereviewerapp;

import com.google.gson.Gson;
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.Details;
import com.reynax.moviereviewerapp.data.MovieDetails;
import com.reynax.moviereviewerapp.data.SeriesDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

public class Globals {
    public enum DATA_TYPE{MOVIES, SERIES, CELEBRITIES}

    public enum ACTIVITY_TYPE{MAIN, VERTICAL_LIST}

    public static final int SLIDE_DURATION = 5000;


    public static void loadDetails(List<Content> items, Globals.DATA_TYPE dataType) {
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

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
