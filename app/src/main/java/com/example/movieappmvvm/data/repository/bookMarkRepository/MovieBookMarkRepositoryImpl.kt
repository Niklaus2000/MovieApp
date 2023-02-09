package com.example.movieappmvvm.data.repository.bookMarkRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.cache.MovieDao
import com.example.movieappmvvm.data.model.MovieDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieBookMarkRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
): MovieBookMarkRepository {
    override suspend fun searchMovie(query: String): Flow<List<MovieDB>> {
        return movieDao.searchMovie(query)
    }

    override suspend fun getAllBookMark(): Flow<List<MovieDB>> {
        return movieDao.getAllMovies()
    }
}