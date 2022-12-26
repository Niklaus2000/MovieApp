package com.example.movieappmvvm.data.repository.moviesRepository

import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getNowPlayingMovie(): Flow<HandleResponse<MovieResponse>>
    suspend fun getUpcomingMovie(): Flow<HandleResponse<MovieResponse>>
    suspend fun getPopularMovie(): Flow<HandleResponse<MovieResponse>>
    suspend fun getTopRatedMovie(): Flow<HandleResponse<MovieResponse>>
}