package com.fadybassem.data.remote.mapper

import com.fadybassem.data.remote.response.MovieResponseModel
import com.fadybassem.data.remote.response.MoviesResponseModel
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies

fun MoviesResponseModel.toMoviesDomain(): Movies {
    return Movies(
        page = page,
        results = results.map { it.toMovieDomain() } as ArrayList<Movie>,
        totalPages = totalPages,
        totalResults = totalResults,
    )
}

fun MovieResponseModel.toMovieDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
