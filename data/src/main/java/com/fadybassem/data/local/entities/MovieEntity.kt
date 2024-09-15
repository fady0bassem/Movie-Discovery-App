package com.fadybassem.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean?,
    val genreNames: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    var isPopular: Boolean?,
    var page: Int?,
    val similarMovies: String?, // JSON string or comma-separated list
    var isInWatchlist: Boolean = false
)
