package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieAndGenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<GenreEntity>)

    @Query("SELECT * FROM movie where is_favorite = 1")
    fun getAllFavoriteMovie(): Flow<List<MovieAndGenreEntity>>

    @Query("UPDATE movie SET is_favorite = :isFavorite WHERE movieId = :movieId")
    fun setFavoriteMovie(movieId: Long, isFavorite: Boolean)

    @Query("SELECT * FROM movie WHERE movieId = :movieId LIMIT 1")
    fun cekFavoriteMovieById(movieId: Long): Flow<MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie")
    fun getAllMovieAndGenre(): Flow<List<MovieAndGenreEntity>>
}