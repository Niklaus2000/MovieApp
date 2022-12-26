package com.example.movieappmvvm.data.repository.viewAllRepository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface ViewAllRepository {
    suspend fun getPopularMovieResult(): Flow<PagingData<Movie>>
    suspend fun getUpcomingMovieResult(): Flow<PagingData<Movie>>
    suspend fun getTopRatedMovieResult(): Flow<PagingData<Movie>>
}