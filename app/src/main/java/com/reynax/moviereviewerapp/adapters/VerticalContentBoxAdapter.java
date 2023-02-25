package com.reynax.moviereviewerapp.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.reynax.moviereviewerapp.Globals;
import com.reynax.moviereviewerapp.JSONTask;
import com.reynax.moviereviewerapp.Private;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.Movie;
import com.reynax.moviereviewerapp.data.MovieDetails;
import com.reynax.moviereviewerapp.data.OnItemClickListener;
import com.reynax.moviereviewerapp.data.Series;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VerticalContentBoxAdapter extends RecyclerView.Adapter<VerticalContentBoxAdapter.ViewHolder> {

    private List<Content> items;
    private int pagesLoaded;

    private final OnItemClickListener listener;

    public VerticalContentBoxAdapter(@NonNull List<Content> items, @NonNull OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;

        this.pagesLoaded = 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_v_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void filter(List<Content> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public List<Content> getItems(){
        return items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView poster;
        private final TextView title;
        private final TextView rating;
        private final TextView year;
        private final TextView length;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.v_content_box_iv_poster);
            rating = itemView.findViewById(R.id.v_content_box_tv_rating);
            title = itemView.findViewById(R.id.v_content_box_tv_title);
            year = itemView.findViewById(R.id.v_content_box_tv_year);
            length = itemView.findViewById(R.id.v_content_box_tv_length);
        }

        public void bind(Content item, OnItemClickListener listener){
            this.rating.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(item.getRating())));
            this.title.setText(item.getTitle());

            if (item.getPosterPath() != null) {
                String posterPath = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();
                Glide.with(itemView.getContext()).load(posterPath).placeholder(R.drawable.placeholder).into(this.poster);
            } else this.poster.setImageResource(R.drawable.placeholder);

            if(item instanceof Movie && item.getDetails() != null){
                String releaseDate = ((Movie) item).getReleaseData();
                long length = ((MovieDetails)item.getDetails()).getRuntime();

                LocalDate date = LocalDate.parse(releaseDate);
                this.year.setText(String.format(Locale.ENGLISH, "%d", date.getYear()));
                this.length.setText(String.format(Locale.ENGLISH, "%d min", length));
            }else if(item instanceof Series && item.getDetails() != null){
                Series series = (Series) item;
                long seasons = ((SeriesDetails)series.getDetails()).getNumberOfSeasons();
                long episodes = ((SeriesDetails)series.getDetails()).getNumberOfEpisodes();

                this.year.setText(String.format(Locale.ENGLISH, "%d seasons", seasons));
                this.length.setText(String.format(Locale.ENGLISH, "%d episodes", episodes));
            }

            itemView.setOnClickListener(view -> listener.onItemClick(item));
        }
    }

    public void loadMore(String q, Context context, Button loadMoreButton){
        new LoadMoreTask(context, loadMoreButton).execute(q);
    }

    public class LoadMoreTask extends AsyncTask<String, Void, List<Content>>{
        private final Context context;
        private final Button loadMoreButton;
        public LoadMoreTask(@NonNull Context context, @NonNull Button loadMoreButton){
            this.context = context;
            this.loadMoreButton = loadMoreButton;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadMoreButton.setText(context.getString(R.string.v_list_load_more_loading));
            loadMoreButton.setActivated(false);
        }

        @Override
        protected List<Content> doInBackground(String... strings) {
            List<Content> movies = new ArrayList<>();
            Globals.DATA_TYPE dataType;
            if(items == null || items.size() == 0)
                return movies;

            if(items.get(0) instanceof Movie)
                dataType = Globals.DATA_TYPE.MOVIES;
            else if(items.get(0) instanceof Series)
                dataType = Globals.DATA_TYPE.SERIES;
            else return movies;

            try {
                URL url = new URL(strings[0] + "?api_key=" + Private.API_KEY + "&language=en-US&page=" + (pagesLoaded + 1));
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

        @Override
        protected void onPostExecute(List<Content> contents) {
            super.onPostExecute(contents);
            if(contents != null && contents.size() > 0) {
                items.addAll(contents);
                notifyDataSetChanged();
                ++pagesLoaded;
            }
            loadMoreButton.setText(context.getString(R.string.v_list_load_more));
            loadMoreButton.setActivated(true);
        }
    }
}
