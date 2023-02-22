package com.reynax.moviereviewerapp.data;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.reynax.moviereviewerapp.activities.ContentDetailsActivity;

import java.time.LocalDate;
import java.util.Locale;

public class ContentDetailsListener implements OnItemClickListener{

    private final Context context;

    public ContentDetailsListener(@NonNull Context context){
        this.context = context;
    }

    @Override
    public void onItemClick(Content item) {
        String description = generateDescription(item);

        Intent intent = new Intent(context, ContentDetailsActivity.class);
        intent.putExtra("title", item.getTitle());
        intent.putExtra("description", description);
        intent.putExtra("backdrop", item.getBackdropPath());
        intent.putExtra("poster", item.getPosterPath());
        intent.putExtra("genres", item.getGenreIds());
        intent.putExtra("overview", item.getOverview());
        intent.putExtra("rating", item.getRating());
        intent.putExtra("description", description);
        context.startActivity(intent);
    }

    private static String generateDescription(Content item){
        String result = "";
        if(item instanceof Movie){
            MovieDetails details = (MovieDetails) item.getDetails();
            if(details != null){
                String releaseDate = details.getReleaseDate();
                LocalDate localDate = LocalDate.parse(releaseDate);
                result = String.format(Locale.ENGLISH, "%d %d min", localDate.getYear(), details.getRuntime());
            }
        }else if(item instanceof Series){
            SeriesDetails details = (SeriesDetails) item.getDetails();
            if(details != null){
                String releaseDate = details.getFirstAirDate();
                LocalDate localDate = LocalDate.parse(releaseDate);
                result = String.format(Locale.ENGLISH, "%d %d episodes", localDate.getYear(), details.getNumberOfEpisodes());
            }
        }
        return result;
    }
}
