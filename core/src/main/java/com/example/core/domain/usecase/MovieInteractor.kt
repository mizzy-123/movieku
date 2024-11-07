package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie()
    override fun getDetailMovie(movieId: Long): Flow<Resource<DetailMovie>> = movieRepository.getDetailMovie(movieId)
    override fun setFavorite(movieId: Long, isFavorite: Boolean) {
        movieRepository.setFavorite(movieId, isFavorite)
    }

    override fun getAllFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getAllFavoriteMovie()
    }

    override fun cekFavoriteMovieById(movieId: Long): Flow<Boolean> {
        return movieRepository.cekFavoriteMovieById(movieId)
    }
}