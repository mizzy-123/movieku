package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genreId")
    val genreId: Long = 0,

    @ColumnInfo(name = "movieId")
    val movieId: Long,

    @ColumnInfo(name = "name")
    val name: String,
)
