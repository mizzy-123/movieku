package com.example.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase

class FavoriteViewModel (private val movieUseCase: MovieUseCase): ViewModel() {

    fun getAllFavoriteMovie(): LiveData<List<Movie>> = movieUseCase.getAllFavoriteMovie().asLiveData()

}