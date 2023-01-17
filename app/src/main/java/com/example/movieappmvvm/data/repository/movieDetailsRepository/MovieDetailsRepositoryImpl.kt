package com.example.movieappmvvm.data.repository.movieDetailsRepository

import com.example.movieappmvvm.core.HandleApiRequest
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.cache.MovieDao
import com.example.movieappmvvm.data.model.CastCreditsResponse
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.MovieDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val handleApiRequest: HandleApiRequest ,
    private val moviesService: MoviesService ,
    private val movieDao: MovieDao
) : MovieDetailsRepository {

    override suspend fun getMoviesDetails(movie_id: Int): Flow<HandleResponse<Movie>> =
     handleApiRequest.handleApiRequest {
        moviesService.getMovieById(movie_id)
     }

    override suspend fun loadCast(movie_id: Int): Flow<HandleResponse<CastCreditsResponse>> =
       handleApiRequest.handleApiRequest {
           moviesService.getMovieCredits(movie_id)
       }

    override suspend fun insertMovie(Movie: MovieDB) {
        return movieDao.insertMovie(Movie)
    }

    override suspend fun getAllMovie(): List<MovieDB> {
        return movieDao.getAllMovies()
    }

    override suspend fun removeMovie(movie: MovieDB) {
        return movieDao.removeMovie(movie)
    }

    override  fun bookmarkExist(id: Int): Boolean {
        return movieDao.bookmarkExist(id)
    }

    override suspend fun searchMovie(query: String): Flow<List<MovieDB>> {
        return searchMovie(query)
    }
}


