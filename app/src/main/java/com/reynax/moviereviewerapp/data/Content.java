package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public interface Content {
    String getPosterPath();

    long getId();

    double getPopularity();

    String getBackdropPath();

    String getRating();

    String getOverview();

    int[] getGenreIds();

    String getOriginalLanguage();

    String getTitle();

    long getVoteCount();

    Details getDetails();

    void setDetails(Details details);
}
