package com.example.movieku.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDetailMovie(movieId: Long): LiveData<Resource<DetailMovie>> =
        movieUseCase.getDetailMovie(movieId).asLiveData()

    fun cekFavoriteMovieById(movieId: Long): LiveData<Boolean> =
        movieUseCase.cekFavoriteMovieById(movieId).asLiveData()

    fun setFavorite(movieId: Long, isFavorite: Boolean) {
        movieUseCase.setFavorite(movieId, isFavorite)
    }
}