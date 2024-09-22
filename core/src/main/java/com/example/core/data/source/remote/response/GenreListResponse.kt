package com.example.core.data.source.remote.response

data class GenreListResponse(
    val genres: List<DataGenre>
)

data class DataGenre(
    val id: Int,
    val name: String
)