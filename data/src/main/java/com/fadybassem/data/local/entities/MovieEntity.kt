package com.fadybassem.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: String?, // Will be stored as JSON string using TypeConverter
    val budget: Int?,
    val genreIds: String?, // Will be stored as JSON string using TypeConverter
    val genres: String?, // Will be stored as JSON string using TypeConverter
    val genreNames: String?,
    val homepage: String?,
    val imdbId: String?,
    val originCountry: String?, // Will be stored as JSON string using TypeConverter
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: String?, // Will be stored as JSON string using TypeConverter
    val productionCountries: String?, // Will be stored as JSON string using TypeConverter
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: String?, // Will be stored as JSON string using TypeConverter
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    var isPopular: Boolean?,
    var page: Int,
    val similarMovies: String? // JSON string or comma-separated list
)
