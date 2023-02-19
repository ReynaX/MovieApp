package com.reynax.moviereviewerapp.data;

import com.google.gson.annotations.SerializedName;

public class ContentOutput {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private Object[] results;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("totalPages")
    private int totalPages;

    public ContentOutput(int page, Object[] results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public Object[] getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
