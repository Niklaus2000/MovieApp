package com.example.movieappmvvm.data.repository.searchRepository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieappmvvm.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
     fun getSearchResult(query: String): Flow<PagingData<Movie>>
}