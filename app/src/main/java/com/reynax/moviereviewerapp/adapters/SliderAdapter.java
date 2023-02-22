package com.reynax.moviereviewerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reynax.moviereviewerapp.R;
import com.reynax.moviereviewerapp.data.Content;
import com.reynax.moviereviewerapp.data.OnItemClickListener;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
    private final List<Content> items;
    private final OnItemClickListener listener;

    public SliderAdapter(@NonNull List<Content> items, @NonNull OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
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

        public void bind(Content item, OnItemClickListener listener){
            this.title.setText(item.getTitle());

            if(item.getPosterPath() != null) {
                String posterPath = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();
                Glide.with(itemView).load(posterPath).placeholder(R.drawable.placeholder).into(this.poster);
            }else this.poster.setImageResource(R.drawable.placeholder);
            if(item.getBackdropPath() != null) {
                String backdropPath = "https://image.tmdb.org/t/p/w500" + item.getBackdropPath();
                Glide.with(itemView).load(backdropPath).placeholder(R.drawable.placeholder).into(this.backdrop);
            }else this.backdrop.setImageResource(R.drawable.placeholder);

            itemView.setOnClickListener(view -> listener.onItemClick(item));
        }
    }
}
