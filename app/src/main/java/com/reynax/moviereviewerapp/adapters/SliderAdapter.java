package com.reynax.moviereviewerapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.data.Movie;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private final List<Movie> movies;

    private final Fragment fragment;

    public SliderAdapter(@NonNull Fragment fragment, @NonNull List<Movie> movies){
        this.movies = movies;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        String posterPath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        String backdropPath = "https://image.tmdb.org/t/p/w500" + movie.getBackdropPath();

        Glide.with(fragment).load(posterPath).placeholder(R.drawable.placeholder).into(holder.poster);
        Glide.with(fragment).load(backdropPath).placeholder(R.drawable.placeholder).into(holder.backdrop);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView backdrop;

        private final ImageView poster;

        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            backdrop = itemView.findViewById(R.id.item_slider_iv_backdrop);
            poster = itemView.findViewById(R.id.item_slider_iv_poster);
            title = itemView.findViewById(R.id.item_slider_tv_title);
        }
    }
}
