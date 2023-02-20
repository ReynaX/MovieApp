package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class Movie implements Content {

    private Details details;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("adult")
    private final boolean adult;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("release_date")
    private final String releaseData;

    @SerializedName("genre_ids")
    private final int[] genreIds;

    @SerializedName("id")
    private final long id;

    @SerializedName("original_language")
    private final String originalLanguage;
    @SerializedName("original_title")
    private final String originalTitle;

    @SerializedName("title")
    private final String title;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("popularity")
    private final Double popularity;

    @SerializedName("vote_count")
    private final long voteCount;

    @SerializedName("video")
    private final boolean video;

    @SerializedName("vote_average")
    private final String rating;

    public Movie(String posterPath, boolean adult, String overview, String releaseData,
                 int[] genreIds, long id, String originalLanguage, String originalTitle,
                 String title, String backdropPath, Double popularity, long voteCount,
                 boolean video, String rating) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseData = releaseData;
        this.genreIds = genreIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.rating = rating;
        this.details = null;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    public String getReleaseData() {
        return releaseData;
    }

    @Override
    public int[] getGenreIds() {
        return genreIds;
    }

    @Override
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public double getPopularity() {
        return popularity;
    }

    @Override
    public long getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public Details getDetails() {
        return details;
    }

    @Override
    public void setDetails(Details details) {
        this.details = details;
    }
}
