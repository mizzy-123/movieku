package com.example.core.domain.model

import com.example.core.data.source.local.entity.GenreEntity

data class Movie(
    val movieId: Long,
    val title: String,
    val popularity: Double,
    val release_date: String,
    val original_title: String,
    val poster_path: String,
    val overview: String,
    val vote_average: Float,
    val vote_count: Long,
    val genre_list: List<GenreEntity>,
    val is_favorite: Boolean
)
