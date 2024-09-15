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
        adult = adult,
        backdropPath = backdropPath?.let { IMAGE_BASE_URL + it },
        belongsToCollection = converter.toBelongsToCollectionList(belongsToCollection),
        budget = budget,
        genreIds = converter.toIntList(genreIds) ?: arrayListOf(),
        genres = converter.toGenresList(genres) ?: arrayListOf(),
        genreNames = converter.toGenresList(genres)?.map { it.name }?.joinToString(", "),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originCountry = converter.toStringList(originCountry) ?: arrayListOf(),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath?.let { IMAGE_BASE_URL + it },
        productionCompanies = converter.toProductionCompaniesList(productionCompanies)
            ?: arrayListOf(),
        productionCountries = converter.toProductionCountriesList(productionCountries)
            ?: arrayListOf(),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = converter.toSpokenLanguagesList(spokenLanguages) ?: arrayListOf(),
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        isPopular = isPopular,
        similarMovies = converter.toIntList(similarMovies)?: arrayListOf()
    )
}

fun List<MovieEntity>.toMoviesListDomain(): ArrayList<Movie> {
    return map { it.toMovieDomain() } as ArrayList<Movie>
}


fun Movie.toMovieEntity(isPopular: Boolean = false, page: Int = 1): MovieEntity {
    return MovieEntity(
        adult = adult,
        backdropPath = backdropPath?.let { IMAGE_BASE_URL + it },
        belongsToCollection = converter.fromBelongsToCollectionList(belongsToCollection),
        budget = budget,
        genreIds = converter.fromIntList(genreIds),
        genres = converter.fromGenresList(genres),
        genreNames = genres.map { it.name }.joinToString(", "),
        homepage = homepage,
        id = id ?: -1,
        imdbId = imdbId,
        originCountry = converter.fromStringList(originCountry),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath?.let { IMAGE_BASE_URL + it },
        productionCompanies = converter.fromProductionCompaniesList(productionCompanies),
        productionCountries = converter.fromProductionCountriesList(productionCountries),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = converter.fromSpokenLanguagesList(spokenLanguages),
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        isPopular = isPopular,
        page = page,
        similarMovies = converter.fromIntList(similarMovies)
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