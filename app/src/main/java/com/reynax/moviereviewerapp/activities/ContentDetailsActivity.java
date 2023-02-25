package com.reynax.moviereviewerapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.R;

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
    private RecyclerView similar;

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
        similar = findViewById(R.id.content_details_rv_similar);

        Bundle extras = getIntent().getExtras();
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
        rating.setText(String.format(Locale.ENGLISH, "%.1f/10", Double.valueOf((String) extras.get("rating"))));
        double popularityValue = (Double)extras.get("popularity");
        popularity.setText(String.valueOf(popularityValue));

        description.setText((String)extras.get("description"));
        int[] genresIds = (int[]) extras.get("genres");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0, 5, 0);

        for(int i = 0; i < Math.min(genresIds.length, 3); ++i){
            TextView genre = new TextView(this);
            genre.setLayoutParams(params);
            genre.setTextColor(Color.WHITE);
            genre.setTextSize(13);
            genre.setPadding(5, 0, 5, 0);

            Drawable background = getResources().getDrawable(R.drawable.genre_border);
            genre.setBackground(background);
            genre.setText(Globals.getGenreName(genresIds[i]));
            genresLayout.addView(genre);
        }

        String type = (String)extras.get("type");
        new JSONTask(similar, type.equals("movie") ? Globals.DATA_TYPE.MOVIES : Globals.DATA_TYPE.SERIES,
                Globals.ACTIVITY_TYPE.MAIN).execute(String.format(Locale.ENGLISH,
                "https://api.themoviedb.org/3/%s/%d/similar", type, (long) extras.get("id")));
    }

    public void onBackButtonPressed(View view) {
        onBackPressed();
    }
}