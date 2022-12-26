package com.example.movieappmvvm.data.repository.moviesRepository

import com.example.movieappmvvm.core.HandleApiRequest
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.model.MovieResponse
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService ,
    private val handleApiRequest: HandleApiRequest ,
) : MoviesRepository {
    override suspend fun getNowPlayingMovie(): Flow<HandleResponse<MovieResponse>> =
        handleApiRequest.handleApiRequest {
            moviesService.getNowPlayingMovies()
        }
    override suspend fun getUpcomingMovie(): Flow<HandleResponse<MovieResponse>> =
       handleApiRequest.handleApiRequest {
           moviesService.getUpcomingMovies()

       }
    override suspend fun getPopularMovie(): Flow<HandleResponse<MovieResponse>>  =
        handleApiRequest.handleApiRequest {
            moviesService.getPopularMovies(CONSTANTS.API_KEY,1)
        }
    override suspend fun getTopRatedMovie(): Flow<HandleResponse<MovieResponse>> =
        handleApiRequest.handleApiRequest {
            moviesService.getTopRatedMovies()
        }
}


