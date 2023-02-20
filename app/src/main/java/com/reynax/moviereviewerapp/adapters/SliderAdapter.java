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

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private final List<Content> items;

    private final Context context;

    public SliderAdapter(@NonNull Context context, @NonNull List<Content> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content content = items.get(position);
        holder.title.setText(content.getTitle());

        if(content.getPosterPath() != null) {
            String posterPath = "https://image.tmdb.org/t/p/w500" + content.getPosterPath();
            Glide.with(context).load(posterPath).placeholder(R.drawable.placeholder).into(holder.poster);
        }else holder.poster.setImageResource(R.drawable.placeholder);
        if(content.getBackdropPath() != null) {
            String backdropPath = "https://image.tmdb.org/t/p/w500" + content.getBackdropPath();
            Glide.with(context).load(backdropPath).placeholder(R.drawable.placeholder).into(holder.backdrop);
        }else holder.backdrop.setImageResource(R.drawable.placeholder);
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
    }
}
