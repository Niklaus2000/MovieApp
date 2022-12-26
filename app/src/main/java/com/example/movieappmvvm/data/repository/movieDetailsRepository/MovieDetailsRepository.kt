package com.example.movieappmvvm.data.repository.movieDetailsRepository

import androidx.lifecycle.LiveData
import com.example.movieappmvvm.core.response.DetailsUIState
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.model.CastCreditsResponse
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository  {
    suspend fun getMoviesDetails(movie_id: Int): Flow<HandleResponse<Movie>>
    suspend fun loadCast(movie_id: Int): Flow<HandleResponse<CastCreditsResponse>>

}