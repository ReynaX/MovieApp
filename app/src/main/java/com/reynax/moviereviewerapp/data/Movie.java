package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class Movie implements Content {
    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("adult")
    private final boolean adult;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("release_date")
    private final String releaseData;

    @SerializedName("genre_ids")
    private final int[] genresIds;

    @SerializedName("id")
    private final int id;

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
    private final int voteCount;

    @SerializedName("video")
    private final boolean video;

    @SerializedName("vote_average")
    private final String rating;

    public Movie(String posterPath, boolean adult, String overview, String releaseData,
                 int[] genresIds, int id, String originalLanguage, String originalTitle,
                 String title, String backdropPath, Double popularity, int voteCount,
                 boolean video, String rating) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseData = releaseData;
        this.genresIds = genresIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.rating = rating;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public int[] getGenreIds() {
        return genresIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public String getRating() {
        return rating;
    }
}
