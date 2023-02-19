package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public interface Content {
    String getPosterPath();
    int getId();
    double getPopularity();
    String getBackdropPath();
    String getRating();
    String getOverview();
    int[] getGenreIds();
    String getOriginalLanguage();
    String getTitle();
    int getVoteCount();
}
