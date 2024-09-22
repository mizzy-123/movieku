package com.example.core.data.source.remote.network

import com.example.core.BuildConfig
import com.example.core.data.source.remote.response.GenreListResponse
import com.example.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: Bearer ${BuildConfig.TOKEN_MOVIEDB}")
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("include_adult") include_adult: Boolean = false,
        @Query("include_video") include_video: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sort_by: String = "popularity.desc"
    ): MovieListResponse

    @Headers("Authorization: Bearer ${BuildConfig.TOKEN_MOVIEDB}")
    @GET("genre/movie/list")
    suspend fun getGenreList(): GenreListResponse
}