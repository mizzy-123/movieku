package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    suspend fun insertMovie(movie: List<MovieEntity>) {
        movieDao.insertMovie(movie)
    }
}