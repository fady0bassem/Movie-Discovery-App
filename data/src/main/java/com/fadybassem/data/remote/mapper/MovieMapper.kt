package com.fadybassem.data.remote.mapper

import com.fadybassem.data.remote.response.BelongsToCollectionResponseModel
import com.fadybassem.data.remote.response.CastResponseModel
import com.fadybassem.data.remote.response.CreditsResponseModel
import com.fadybassem.data.remote.response.CrewResponseModel
import com.fadybassem.data.remote.response.GenresResponseModel
import com.fadybassem.data.remote.response.MovieResponseModel
import com.fadybassem.data.remote.response.MoviesResponseModel
import com.fadybassem.data.remote.response.ProductionCompaniesResponseModel
import com.fadybassem.data.remote.response.ProductionCountriesResponseModel
import com.fadybassem.data.remote.response.SpokenLanguagesResponseModel
import com.fadybassem.domain.model.BelongsToCollection
import com.fadybassem.domain.model.Cast
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.model.Crew
import com.fadybassem.domain.model.Genres
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.model.ProductionCompanies
import com.fadybassem.domain.model.ProductionCountries
import com.fadybassem.domain.model.SpokenLanguages
import com.fadybassem.util.AppConfiguration.Companion.IMAGE_BASE_URL

fun MoviesResponseModel.toMoviesDomain(): Movies {
    return Movies(
        page = page,
        results = results.map { it.toMovieDomain() } as ArrayList<Movie>,
        totalPages = totalPages,
        totalResults = totalResults,
    )
}

fun MovieResponseModel.toMovieDomain(): Movie {
    return Movie(adult = adult,
        backdropPath = backdropPath?.let { IMAGE_BASE_URL + it },
        belongsToCollection = belongsToCollection?.toBelongsToCollectionDomain(),
        budget = budget,
        genreIds = genreIds,
        genres = genres.map { it.toGenresDomain() } as ArrayList<Genres>,
        genreNames = genres.map { it.name }.joinToString(", "),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath?.let { IMAGE_BASE_URL + it },
        productionCompanies = productionCompanies.map { it.toProductionCompaniesDomain() } as ArrayList<ProductionCompanies>,
        productionCountries = productionCountries.map { it.toProductionCountriesDomain() } as ArrayList<ProductionCountries>,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.toSpokenLanguagesDomain() } as ArrayList<SpokenLanguages>,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount)
}

fun BelongsToCollectionResponseModel.toBelongsToCollectionDomain(): BelongsToCollection {
    return BelongsToCollection(
        id = id,
        name = name,
        posterPath = posterPath?.let { IMAGE_BASE_URL + it },
        backdropPath = backdropPath?.let { IMAGE_BASE_URL + it },
    )
}

fun GenresResponseModel.toGenresDomain(): Genres {
    return Genres(id = id, name = name)
}

fun ProductionCompaniesResponseModel.toProductionCompaniesDomain(): ProductionCompanies {
    return ProductionCompanies(
        id = id,
        logoPath = logoPath?.let { IMAGE_BASE_URL + it },
        name = name,
        originCountry = originCountry
    )
}

fun ProductionCountriesResponseModel.toProductionCountriesDomain(): ProductionCountries {
    return ProductionCountries(iso31661 = iso31661, name = name)
}

fun SpokenLanguagesResponseModel.toSpokenLanguagesDomain(): SpokenLanguages {
    return SpokenLanguages(englishName = englishName, iso6391 = iso6391, name = name)
}

fun CreditsResponseModel.toCreditsDomain(): Credits {
    return Credits(
        id = id,
        cast = cast.map { it.toCastResponseModelDomain() } as ArrayList<Cast>,
        crew = crew.map { it.toCrewResponseModelDomain() } as ArrayList<Crew>)
}

fun CastResponseModel.toCastResponseModelDomain(): Cast {
    return Cast(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath?.let { IMAGE_BASE_URL + it },
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )
}

fun CrewResponseModel.toCrewResponseModelDomain(): Crew {
    return Crew(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath?.let { IMAGE_BASE_URL + it },
        creditId = creditId,
        department = department,
        job = job
    )
}