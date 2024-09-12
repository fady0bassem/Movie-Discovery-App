package com.fadybassem.domain.model

data class Movies(
    var page: Int? = null,
    var results: ArrayList<Movie> = arrayListOf(),
    var totalPages: Int? = null,
    var totalResults: Int? = null,
)