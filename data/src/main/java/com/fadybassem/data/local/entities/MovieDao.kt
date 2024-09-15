package com.fadybassem.data.local.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE isPopular = 1 ORDER BY popularity DESC")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE page = :page AND isPopular = 0 AND releaseDate LIKE '2024%' ORDER BY releaseDate DESC")
    suspend fun getMoviesByPage(page: Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM movies WHERE id IN (:ids)")
    suspend fun getMoviesByIds(ids: List<Int>): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query(
        """
        UPDATE movies 
        SET 
            adult = :adult,
            genreNames = :genreNames,
            originalTitle = :originalTitle,
            overview = :overview,
            popularity = :popularity,
            posterPath = :posterPath,
            releaseDate = :releaseDate,
            similarMovies = :similarMovies,
            isInWatchlist = :isInWatchlist,
            page = :page
        WHERE id = :id
    """
    )
    suspend fun updateMovieFields(
        id: Int?,
        adult: Boolean?,
        genreNames: String?,
        originalTitle: String?,
        overview: String?,
        popularity: Double?,
        posterPath: String?,
        releaseDate: String?,
        similarMovies: String?,
        isInWatchlist: Boolean?,
        page: Int? = null,
    )

    @Query("SELECT * FROM credits WHERE id = :id")
    suspend fun getCreditsById(id: Int): CreditsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCredits(creditsEntity: CreditsEntity)

    @Query("UPDATE movies SET similarMovies = :similarMovies WHERE id = :id")
    suspend fun updateSimilarMovies(id: Int, similarMovies: String?)

    @Query("UPDATE movies SET isInWatchlist = 1 WHERE id = :movieId")
    suspend fun addToWatchlist(movieId: Int)

    @Query("UPDATE movies SET isInWatchlist = 0 WHERE id = :movieId")
    suspend fun removeFromWatchlist(movieId: Int)

    @Query("SELECT * FROM movies WHERE isInWatchlist = 1 ORDER BY releaseDate DESC")
    suspend fun getWatchlistMovies(): List<MovieEntity>
}
