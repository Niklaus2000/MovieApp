package com.example.movieappmvvm.core.response

import androidx.paging.PagingData
import com.example.movieappmvvm.data.model.Movie

sealed class UiState {
    object Loading : UiState()
    class Success(val data: PagingData<Movie>): UiState()
    class Error(val message: String): UiState()
 }