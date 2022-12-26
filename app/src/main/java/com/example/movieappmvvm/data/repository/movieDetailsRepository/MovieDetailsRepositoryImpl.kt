package com.example.movieappmvvm.data.repository.movieDetailsRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.movieappmvvm.core.HandleApiRequest
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.model.CastCreditsResponse
import com.example.movieappmvvm.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val handleApiRequest: HandleApiRequest ,
    private val moviesService: MoviesService ,
) : MovieDetailsRepository {

    override suspend fun getMoviesDetails(movie_id: Int): Flow<HandleResponse<Movie>> =
     handleApiRequest.handleApiRequest {
        moviesService.getMovieById(movie_id)
     }

//    override suspend fun getMoviesDetails(movie_id: Int): LiveData<Movie> = liveData {
//        val response = moviesService.getMovieById(movie_id = movie_id).body()
//        emit(response!!)
//    }


    override suspend fun loadCast(movie_id: Int): Flow<HandleResponse<CastCreditsResponse>> =
       handleApiRequest.handleApiRequest {
           moviesService.getMovieCredits(movie_id)
       }
}


