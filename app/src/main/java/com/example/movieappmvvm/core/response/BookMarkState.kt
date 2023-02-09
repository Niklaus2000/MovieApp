package com.example.movieappmvvm.core.response

import com.example.movieappmvvm.data.model.MovieDB

sealed class BookMarkState {
    class Error(val message: String) : BookMarkState()
    class Success(val data: List<MovieDB>): BookMarkState()
}