package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class MovieDetails implements Details{
    @SerializedName("budget")
    private int budget;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("revenue")
    private Long revenue;

    @SerializedName("runtime")
    private Long runtime;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;

    public MovieDetails(int budget, String homepage, String releaseDate,
                        Long revenue, Long runtime, String status, String tagline) {
        this.budget = budget;
        this.homepage = homepage;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
    }

    public int getBudget() {
        return budget;
    }

    @Override
    public String getHomepage() {
        return homepage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Long getRevenue() {
        return revenue;
    }

    public Long getRuntime() {
        return runtime;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }
}
