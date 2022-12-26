package com.example.movieappmvvm.data.repository.viewAllRepository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.ui.paging.PopularPagingSource
import com.example.movieappmvvm.ui.paging.TopRatedPagingSource
import com.example.movieappmvvm.ui.paging.UpcomingPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ViewAllRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,

) : ViewAllRepository {
    override suspend fun getPopularMovieResult(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 20 ,
                maxSize = 500
            ) ,
            pagingSourceFactory = {
               PopularPagingSource(moviesService)
            }
        ).flow

    override suspend fun getUpcomingMovieResult(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 20 ,
                maxSize = 500
            ) ,
            pagingSourceFactory = {
                UpcomingPagingSource(moviesService)
            }
        ).flow

    override suspend fun getTopRatedMovieResult(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 20 ,
                maxSize = 500
            ) ,
            pagingSourceFactory = {
                TopRatedPagingSource(moviesService)
            }
        ).flow

}