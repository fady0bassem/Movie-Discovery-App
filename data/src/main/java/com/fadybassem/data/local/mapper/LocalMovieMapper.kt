package com.fadybassem.data.local.mapper

import com.fadybassem.data.local.entities.Converters
import com.fadybassem.data.local.entities.CreditsEntity
import com.fadybassem.data.local.entities.MovieEntity
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.model.Movie
import com.fadybassem.util.AppConfiguration.Companion.IMAGE_BASE_URL

val converter = Converters()

fun MovieEntity.toMovieDomain(): Movie {
    return Movie(
        id = id,
        adult = adult,
        genreNames = genreNames,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath?.let { IMAGE_BASE_URL + it },
        releaseDate = releaseDate,
        isPopular = isPopular,
        similarMovies = converter.toIntList(similarMovies)?: arrayListOf(),
        isInWatchlist = isInWatchlist
    )
}

fun List<MovieEntity>.toMoviesListDomain(): ArrayList<Movie> {
    return map { it.toMovieDomain() } as ArrayList<Movie>
}


fun Movie.toMovieEntity(isPopular: Boolean = false, page: Int? = null): MovieEntity {
    return MovieEntity(
        id = id ?: -1,
        adult = adult,
        genreNames = genreNames,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath?.let { IMAGE_BASE_URL + it },
        releaseDate = releaseDate,
        isPopular = isPopular,
        page = page,
        similarMovies = converter.fromIntList(similarMovies),
        isInWatchlist = isInWatchlist
    )
}

fun CreditsEntity.toCreditsDomain(): Credits {
    return Credits(
        id = id,
        cast = converter.toCastList(cast) ?: arrayListOf(),
        crew = converter.toCrewList(crew) ?: arrayListOf(),
    )
}

fun Credits.toCreditsEntity(): CreditsEntity {
    return CreditsEntity(
        id = id ?: -1,
        cast = converter.fromCastList(cast),
        crew = converter.fromCrewList(crew),
    )
}