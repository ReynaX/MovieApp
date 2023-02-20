package com.reynax.moviereviewerapp.adapters;

import android.content.Context;
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

import java.util.List;
import java.util.Locale;

public class HorizontalContentBoxAdapter extends RecyclerView.Adapter<HorizontalContentBoxAdapter.ViewHolder> {

    private final List<Content> items;

    private final Context context;

    public HorizontalContentBoxAdapter(Context context, List<Content> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HorizontalContentBoxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_h_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalContentBoxAdapter.ViewHolder holder, int position) {
        final Content movie = items.get(position);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(movie.getRating())));

        String posterPath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        Glide.with(context).load(posterPath).placeholder(R.drawable.placeholder).into(holder.poster);
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
    }
}
