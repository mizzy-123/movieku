package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieAndGenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(movieId: Long): Flow<Resource<DetailMovieResponse>>

    fun setFavorite(movieId: Long, isFavorite: Boolean)

    fun getAllFavoriteMovie(): Flow<List<Movie>>

    fun cekFavoriteMovieById(movieId: Long): Flow<Boolean>
}