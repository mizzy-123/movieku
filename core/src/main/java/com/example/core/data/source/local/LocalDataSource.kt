package com.example.core.data.source.local

import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieAndGenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getAllMovieAndGenre(): Flow<List<MovieAndGenreEntity>> = movieDao.getAllMovieAndGenre()

    suspend fun insertMovie(movie: List<MovieEntity>) {
        movieDao.insertMovie(movie)
    }

    suspend fun insertGenre(genre: List<GenreEntity>) {
        movieDao.insertGenre(genre)
    }
}