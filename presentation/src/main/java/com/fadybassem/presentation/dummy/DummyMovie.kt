package com.fadybassem.presentation.dummy

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

object DummyMovie {
    val movie = Movie(
        adult = false,
        backdropPath = "$IMAGE_BASE_URL/ww1eIoywghjoMzRLRIcbJLuKnJH.jpg",
        belongsToCollection = BelongsToCollection(
            id = 403374,
            name = "Jack Reacher Collection",
            posterPath = "$IMAGE_BASE_URL/qtafXiYDUMKxzsssU41qWey5oiT.jpg",
            backdropPath = "$IMAGE_BASE_URL/vViRXFnSyGJ2fzMbcc5sqTKswcd.jpg",
        ),
        budget = 60000000,
        genres = arrayListOf(
            Genres(id = 28, name = "Action"), Genres(id = 53, name = "Thriller")
        ),
        genreNames = "Action, Thriller, Comedy, Science Fiction, Adventure, Horror, Fantasy",
        genreIds = arrayListOf(28, 53),
        homepage = null,
        id = 343611,
        imdbId = "tt3393786",
        originCountry = arrayListOf("US"),
        originalLanguage = "en",
        originalTitle = "Jack Reacher: Never Go Back",
        overview = "When Major Susan Turner is arrested for treason, ex-investigator Jack Reacher undertakes the challenging task to prove her innocence and ends up exposing a shocking conspiracy.",
        popularity = 53.46,
        posterPath = "$IMAGE_BASE_URL/cOg3UT2NYWHZxp41vpxAnVCOC4M.jpg",
        productionCompanies = arrayListOf(
            ProductionCompanies(
                id = 82819,
                logoPath = "$IMAGE_BASE_URL/gXfFl9pRPaoaq14jybEn1pHeldr.png",
                name = "Skydance Media",
                originCountry = "US"
            ),
            ProductionCompanies(
                id = 83645, logoPath = null, name = "Huahua Media", originCountry = "CN"
            ),
            ProductionCompanies(
                id = 3407,
                logoPath = "$IMAGE_BASE_URL/iVMjKOFyRvm9PW45lW1wW6TSvnj.png",
                name = "Shanghai Film Group",
                originCountry = "CN"
            ),
            ProductionCompanies(
                id = 21777, logoPath = null, name = "TC Productions", originCountry = "US"
            ),
            ProductionCompanies(
                id = 3407,
                logoPath = "$IMAGE_BASE_URL/gz66EfNoYPqHTYI4q9UEN4CbHRc.png",
                name = "Paramount Pictures",
                originCountry = "US"
            ),
        ),
        productionCountries = arrayListOf(
            ProductionCountries(iso31661 = "CN", name = "China"),
            ProductionCountries(iso31661 = "US", name = "United States of America"),
        ),
        releaseDate = "2016-10-19",
        revenue = 162100000,
        runtime = 118,
        spokenLanguages = arrayListOf(
            SpokenLanguages(englishName = "English", iso6391 = "en", name = "English")
        ),
        status = "Released",
        tagline = "Never give in. Never give up. Never go back.",
        title = "Jack Reacher: Never Go Back",
        video = false,
        voteAverage = 5.984,
        voteCount = 4798,
    )

    val movies = Movies(page = 1, results = arrayListOf(movie, movie, movie, movie, movie), totalPages = 9616, totalResults =192306 )

    val credit = Credits(
        cast = arrayListOf(
            Cast(
                adult = false,
                gender = 2,
                id = 500,
                knownForDepartment = "Acting",
                name = "Tom Cruise",
                originalName = "Tom Cruise",
                popularity = 67.315,
                profilePath = "$IMAGE_BASE_URL/maf8PhSvDCdEwjEMbYfGpojR5RP.jpg",
                castId = 0,
                character = "Jack Reacher",
                creditId = "5573971c9251413f6600024d",
                order = 0
            ), Cast(
                adult = false,
                gender = 1,
                id = 71189,
                knownForDepartment = "Acting",
                name = "Cobie Smulders",
                originalName = "Cobie Smulders",
                popularity = 30.264,
                profilePath = "$IMAGE_BASE_URL/1b0mYokcGsfFRge4bjXlS5ihtek.jpg",
                castId = 5,
                character = "Susan Turner",
                creditId = "55fdcf53c3a368133b0016bb",
                order = 1
            ), Cast(
                adult = false,
                gender = 1,
                id = 1466613,
                knownForDepartment = "Acting",
                name = "Danika Yarosh",
                originalName = "Danika Yarosh",
                popularity = 16.567,
                profilePath = "$IMAGE_BASE_URL/i0Z0XnAFxf2BlhfJe26ach2HAVz.jpg",
                castId = 7,
                character = "Samantha",
                creditId = "55fdcf61925141529f0019b5",
                order = 2
            )
        ), crew = arrayListOf(
            Crew(
                adult = false,
                gender = 2,
                id = 1637825,
                knownForDepartment = "Production",
                name = "James Grant",
                originalName = "James Grant",
                popularity = 3.416,
                profilePath = null,
                creditId = "609846aaa3d027003c40637f",
                department = "Production",
                job = "Unit Production Manager"
            ), Crew(
                adult = false,
                gender = 1,
                id = 1966209,
                knownForDepartment = "Crew",
                name = "Deven MacNair",
                originalName = "Deven MacNair",
                popularity = 7.356,
                profilePath = "/oTyjAPM9yHEbVM3pyOCjurmXkOi.jpg",
                creditId = "652f10f50cb33516f5cb5da8",
                department = "Crew",
                job = "Stunt Driver"
            ), Crew(
                adult = false,
                gender = 2,
                id = 102595,
                knownForDepartment = "Crew",
                name = "Steve Kelso",
                originalName = "Steve Kelso",
                popularity = 4.598,
                profilePath = "/lN0SpBvBH2qSZNX8Dyf3rt5r6nh.jpg",
                creditId = "65615a98706e5600c4baa3b6",
                department = "Crew",
                job = "Stunt Driver"
            )
        )
    )
}