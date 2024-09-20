package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.DataMovieResult
import com.example.core.domain.model.Movie

object DataMapper {
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                original_title = it.original_title,
                poster_path = it.poster_path,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
        }

    fun mapResponseToEntites(input: List<DataMovieResult>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                original_title = it.original_title,
                poster_path = it.poster_path,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
            movieList.add(movie)
        }
        return movieList
    }
}