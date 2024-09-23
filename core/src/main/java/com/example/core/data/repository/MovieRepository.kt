package com.example.core.data.repository

import com.example.core.data.NetworkBoundResource
import com.example.core.data.Resource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.DataMovieResult
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<DataMovieResult>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovieAndGenre().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DataMovieResult>>> {
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<DataMovieResult>) {
                val movieList = DataMapper.mapMovieResponseToMovieEntites(data)
                val genreList = DataMapper.mapMovieResponseToGenreEntities(data)
                localDataSource.insertMovie(movieList)
                localDataSource.insertGenre(genreList)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asFlow()
    }
}