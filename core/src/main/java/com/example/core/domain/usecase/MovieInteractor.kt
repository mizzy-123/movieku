package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie()
    override fun getDetailMovie(movieId: Long): Flow<Resource<DetailMovieResponse>> = movieRepository.getDetailMovie(movieId)
}