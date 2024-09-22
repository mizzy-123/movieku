package com.example.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieAndGenreEntity(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val genre: List<GenreEntity>
)
