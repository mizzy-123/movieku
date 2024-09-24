package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(movieId: Long): Flow<Resource<DetailMovieResponse>>
}