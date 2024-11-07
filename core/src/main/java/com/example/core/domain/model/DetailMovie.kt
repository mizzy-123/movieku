package com.example.core.domain.model

import com.example.core.data.source.remote.response.DataGenre

data class DetailMovie(
    val backdrop_path: String,
    val genres: List<DataGenre>,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val vote_average: Double
)
