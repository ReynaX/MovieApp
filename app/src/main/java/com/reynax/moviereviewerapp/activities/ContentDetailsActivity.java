package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.Movie;
import com.reynax.moviereviewerapp.data.MovieDetails;
import com.reynax.moviereviewerapp.data.Series;
import com.reynax.moviereviewerapp.data.SeriesDetails;

import java.time.LocalDate;
import java.util.Locale;

public class ContentDetailsActivity extends AppCompatActivity {

    private TextView titleBar;
    private TextView titleMain;
    private TextView description;
    private ImageView backdrop;
    private ImageView poster;
    private LinearLayout genresLayout;
    private TextView overview;
    private TextView rating;
    private TextView popularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);

        titleBar = findViewById(R.id.content_details_tv_title_bar);
        titleMain = findViewById(R.id.content_details_tv_title_main);
        description = findViewById(R.id.content_details_tv_description);
        backdrop = findViewById(R.id.content_details_iv_backdrop);
        poster = findViewById(R.id.content_details_tv_poster);
        genresLayout = findViewById(R.id.content_details_ll_genres);
        overview = findViewById(R.id.content_details_tv_overview);
        rating = findViewById(R.id.content_details_tv_rating);
        popularity = findViewById(R.id.content_details_tv_popularity);


        Bundle extras = getIntent().getExtras();
//        Content content = (Content) extras.get("item");
        titleBar.setText((String)extras.get("title"));
        titleMain.setText((String)extras.get("title"));

        String backdropPath = "https://image.tmdb.org/t/p/w500" + (String)extras.get("backdrop");
        String posterPath = "https://image.tmdb.org/t/p/w500" + (String)extras.get("poster");;
        if (extras.get("backdrop") != null)
            Glide.with(this).load(backdropPath).placeholder(R.drawable.placeholder).into(backdrop);
        else Glide.with(this).load(R.drawable.placeholder).into(backdrop);
        if (extras.get("poster") != null)
            Glide.with(this).load(posterPath).placeholder(R.drawable.placeholder).into(poster);
        else Glide.with(this).load(R.drawable.placeholder).into(poster);

        overview.setText((String)extras.get("overview"));
        rating.setText(String.format(Locale.ENGLISH, "%s/10", (String) extras.get("rating")));
//        popularity.setText(((Double) extras.get("popularity")).intValue());

        description.setText((String)extras.get("description"));
        int[] genresIds = (int[]) extras.get("genres");

        for(int i = 0; i < Math.min(genresIds.length, 3); ++i){
            TextView genre = new TextView(this);
            genre.setText(Globals.getGenreName(genresIds[i]));
            genresLayout.addView(genre);
        }
    }

    public void onBackButtonPressed(View view) {
        onBackPressed();
    }
}