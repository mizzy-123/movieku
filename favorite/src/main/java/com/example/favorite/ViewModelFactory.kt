package com.example.favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass){
            FavoriteViewModel::class.java -> FavoriteViewModel(movieUseCase) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}