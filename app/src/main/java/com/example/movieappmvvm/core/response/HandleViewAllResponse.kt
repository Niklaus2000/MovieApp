package com.example.movieappmvvm.core.response

import androidx.paging.PagingData
import com.example.movieappmvvm.data.model.Movie

sealed class HandleViewAllResponse<T>(){
    class Loading<T>() : HandleViewAllResponse<T>()
    class Success(val data: PagingData<Movie>): HandleViewAllResponse<PagingData<Movie>>()
    class Error<T>(val message: String): HandleViewAllResponse<T>()

}

//sealed class HandleViewAllResponse<T> {
//    class Loading<T>: HandleViewAllResponse<T>()
//    class Success(val data: PagingData<Movie>): HandleViewAllResponse<PagingData<Movie>>()
//    class Error<T>(val message: String): HandleViewAllResponse<T>()
//
//}