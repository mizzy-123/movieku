package com.example.core.utils

import com.example.core.data.source.local.entity.GenreEntity
import com.example.core.data.source.local.entity.MovieAndGenreEntity
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.DataMovieResult
import com.example.core.domain.model.Movie

object DataMapper {
    fun mapMovieEntitiesToDomain(input: List<MovieAndGenreEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movie.movieId,
                title = it.movie.title,
                original_title = it.movie.original_title,
                poster_path = it.movie.poster_path,
                overview = it.movie.overview,
                vote_average = it.movie.vote_average,
                vote_count = it.movie.vote_count,
                genre_list = it.genre
            )
        }

    fun mapMovieResponseToMovieEntites(input: List<DataMovieResult>): List<MovieEntity> {
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

    fun mapMovieResponseToGenreEntities(input: List<DataMovieResult>): List<GenreEntity> {
        val genreList = ArrayList<GenreEntity>()
        input.forEach { dataMovie ->
            dataMovie.genre_list.forEach { genre ->
                val genre = GenreEntity(
                    movieId = dataMovie.id,
                    name = genre
                )

                genreList.add(genre)
            }
        }

        return genreList
    }
}