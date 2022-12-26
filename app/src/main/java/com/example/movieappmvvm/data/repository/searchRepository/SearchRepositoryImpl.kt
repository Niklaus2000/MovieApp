package com.example.movieappmvvm.data.repository.searchRepository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.ui.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val apiService: MoviesService,
    ) : SearchRepository {
    override  fun getSearchResult(query: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 20 ,
                maxSize = 500
            ) ,
            pagingSourceFactory = {
                SearchPagingSource(apiService,query)
            }
        ).flow






}