package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.DataGenre
import com.example.core.data.source.remote.response.DataMovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllMovie(): Flow<ApiResponse<List<DataMovieResult>>> {
        return flow {
            try {
                val responseMovie = apiService.getMovieList()
                val responseGenre = apiService.getGenreList()
                val dataArrayMovie = responseMovie.results
                val dataArrayGenre = responseGenre.genres

                if (dataArrayGenre.isEmpty() || dataArrayMovie.isEmpty()){
                    emit(ApiResponse.Empty)
                } else {

                    val genreMap = dataArrayGenre.associateBy { it.id }

                    dataArrayMovie.forEach { movie ->
                        val catchGenre = movie.genre_ids.mapNotNull { genreId ->
                            genreMap[genreId]?.name // Mengambil nama genre sesuai ID
                        }

                        movie.genre_list = catchGenre
                    }
                    emit(ApiResponse.Success(dataArrayMovie))
                }
//                if (dataArray.isNotEmpty()){
//                    emit(ApiResponse.Success(dataArray))
//                } else {
//                    emit(ApiResponse.Empty)
//                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}