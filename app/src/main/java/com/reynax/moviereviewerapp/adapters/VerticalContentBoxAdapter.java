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
import com.reynax.moviereviewerapp.data.Content;

import java.util.List;
import java.util.Locale;

public class VerticalContentBoxAdapter extends RecyclerView.Adapter<VerticalContentBoxAdapter.ViewHolder> {
    private final List<Content> items;
    private final Fragment fragment;

    public VerticalContentBoxAdapter(Fragment fragment, List<Content> items) {
        this.fragment = fragment;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_v_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content item = items.get(position);
        holder.rating.setText(String.format(Locale.ENGLISH, "%.1f", Double.parseDouble(item.getRating())));
        holder.title.setText(item.getTitle());

        String posterPath = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();
        Glide.with(fragment).load(posterPath).placeholder(R.drawable.placeholder).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView poster;

        private final TextView title;

        private final TextView rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.v_content_box_iv_poster);
            rating = itemView.findViewById(R.id.v_content_box_tv_rating);
            title = itemView.findViewById(R.id.v_content_box_tv_title);
        }
    }
}
