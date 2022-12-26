package com.example.movieappmvvm.core.response

sealed class HandleResponse<T> {
    class Loading<T>: HandleResponse<T>()
    class Success<T>(val data: T): HandleResponse<T>()
    class Error<T>(val message: String): HandleResponse<T>()

}