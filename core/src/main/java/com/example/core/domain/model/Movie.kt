package com.example.core.domain.model

data class Movie(
    val movieId: Long,
    val title: String,
    val original_title: String,
    val poster_path: String,
    val overview: String,
    val vote_average: Float,
    val vote_count: Long,
)
