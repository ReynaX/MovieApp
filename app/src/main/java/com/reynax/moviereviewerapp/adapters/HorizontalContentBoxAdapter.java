package com.reynax.moviereviewerapp.adapters;

import android.content.Context;
import android.util.Log;
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
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.Movie;
import com.reynax.moviereviewerapp.data.MovieDetails;
import com.reynax.moviereviewerapp.data.OnItemClickListener;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class HorizontalContentBoxAdapter extends RecyclerView.Adapter<HorizontalContentBoxAdapter.ViewHolder> {

    private final List<Content> items;
    private final OnItemClickListener listener;

    public HorizontalContentBoxAdapter(@NonNull List<Content> items, @NonNull OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HorizontalContentBoxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_h_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalContentBoxAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView rating;
        private final ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.h_content_box_tv_title);
            rating = itemView.findViewById(R.id.h_content_box_tv_rating);
            poster = itemView.findViewById(R.id.h_content_box_iv_poster);
        }

        public void bind(Content item, OnItemClickListener listener){
            this.title.setText(item.getTitle());
            this.rating.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(item.getRating())));

            if(item.getPosterPath() != null) {
                String posterPath = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();
                Glide.with(itemView).load(posterPath).placeholder(R.drawable.placeholder).into(this.poster);
            }else this.poster.setImageResource(R.drawable.placeholder);

            itemView.setOnClickListener(view -> listener.onItemClick(item));
        }
    }
}
