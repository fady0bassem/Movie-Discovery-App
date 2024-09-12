package com.fadybassem.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponseModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieResponseModel> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null,
)