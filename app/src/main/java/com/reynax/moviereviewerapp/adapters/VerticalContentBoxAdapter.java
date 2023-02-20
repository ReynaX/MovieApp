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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VerticalContentBoxAdapter extends RecyclerView.Adapter<VerticalContentBoxAdapter.ViewHolder> {
    private final List<Content> items;
    private final Context context;

    public VerticalContentBoxAdapter(@NonNull Context context, @NonNull List<Content> items) {
        this.context = context;
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

        if (item.getPosterPath() != null) {
            String posterPath = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();
            Glide.with(context).load(posterPath).placeholder(R.drawable.placeholder).into(holder.poster);
        } else holder.poster.setImageResource(R.drawable.placeholder);

        if(item instanceof Movie){
            String releaseDate = ((Movie) item).getReleaseData();
            long length = ((MovieDetails)item.getDetails()).getRuntime();

            LocalDate date = LocalDate.parse(releaseDate);
            holder.year.setText(String.format(Locale.ENGLISH, "%d", date.getYear()));
            holder.length.setText(String.format(Locale.ENGLISH, "%d min", length));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
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
    }
}
