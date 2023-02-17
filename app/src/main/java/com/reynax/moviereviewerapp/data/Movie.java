package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseData;

    @SerializedName("genre_ids")
    private Integer[] genres;

    @SerializedName("id")
    private int id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private String rating;

    public Movie(String posterPath, boolean adult, String overview, String releaseData, Integer[] genres,
                 int id, String originalTitle, String title, String backdropPath, Double popularity,
                 int voteCount, boolean video, String rating) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseData = releaseData;
        this.genres = genres;
        this.id = id;
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

    public Integer[] getGenres() {
        return genres;
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

    public Double getPopularity() {
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
