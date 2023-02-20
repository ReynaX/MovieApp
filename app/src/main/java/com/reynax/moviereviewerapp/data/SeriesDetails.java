package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class SeriesDetails implements Details{
    @SerializedName("episode_run_time")
    private static int[] episodeRunTime;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("in_production")
    private boolean inProduction;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("type")
    private String type;

    public SeriesDetails(String firstAirDate, boolean inProduction, String lastAirDate,
                         int numberOfEpisodes, int numberOfSeasons, String status,
                         String tagline, String type) {
        this.firstAirDate = firstAirDate;
        this.inProduction = inProduction;
        this.lastAirDate = lastAirDate;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.status = status;
        this.tagline = tagline;
        this.type = type;
    }

    public static int[] getEpisodeRunTime() {
        return episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    @Override
    public String getHomepage() {
        return homepage;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getType() {
        return type;
    }
}
