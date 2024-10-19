package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val movieId: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "original_title")
    val original_title: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Float,

    @ColumnInfo(name = "vote_count")
    val vote_count: Long,

    @ColumnInfo(name = "is_favorite")
    var is_favorite: Boolean = false
)
