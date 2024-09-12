package com.fadybassem.domain.model

data class Movie(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIds: ArrayList<Int> = arrayListOf(),
    var id: Int? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
)