package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class Series implements Content{
    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("popularity")
    private final double popularity;

    @SerializedName("id")
    private final int id;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("vote_average")
    private final String rating;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("first_air_date")
    private final String firstAirDate;

    @SerializedName("origin_country")
    private final String[] originCountry;

    @SerializedName("genre_ids")
    private final int[] genreIds;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("vote_count")
    private final int voteCount;

    @SerializedName("name")
    private final String title;

    @SerializedName("original_name")
    private final String originalName;

    public Series(String posterPath, double popularity, int id, String backdropPath,
                  String rating, String overview, String firstAirDate, String[] originCountry,
                  int[] genreIds, String originalLanguage, int voteCount, String title, String originalName) {
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.id = id;
        this.backdropPath = backdropPath;
        this.rating = rating;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.originCountry = originCountry;
        this.genreIds = genreIds;
        this.originalLanguage = originalLanguage;
        this.voteCount = voteCount;
        this.title = title;
        this.originalName = originalName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalName() {
        return originalName;
    }
}
