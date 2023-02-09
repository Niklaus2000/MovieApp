package com.example.movieappmvvm.data.repository.bookMarkRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.model.MovieDB
import kotlinx.coroutines.flow.Flow


interface MovieBookMarkRepository {
    suspend fun searchMovie(query: String): Flow<List<MovieDB>>
    suspend fun getAllBookMark(): Flow<List<MovieDB>>
}