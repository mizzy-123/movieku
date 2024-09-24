package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

    @field:SerializedName("backdrop_path")
    val backdrop_path: String,

    @field:SerializedName("genres")
    val genres: List<DataGenre>,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("vote_average")
    val vote_average: Double
)
