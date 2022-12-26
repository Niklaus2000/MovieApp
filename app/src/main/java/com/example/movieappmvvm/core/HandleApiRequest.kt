package com.example.movieappmvvm.core

import androidx.paging.PagingData
import com.example.movieappmvvm.R
import com.example.movieappmvvm.core.response.HandleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.UnknownHostException

interface HandleApiRequest {
    suspend fun <T> handleApiRequest(apiRequest: suspend () -> Response<T>): Flow<HandleResponse<T>>

    class Base(
        private val provideResources: ProvideResources ,
    ) : HandleApiRequest {

        override suspend fun <T> handleApiRequest(
            apiRequest: suspend () -> Response<T>
        ): Flow<HandleResponse<T>> = flow {
            emit(HandleResponse.Loading())
            try {
                val request = apiRequest.invoke()
                val body = request.body()
                emit(HandleResponse.Success(body!!))
            } catch (noInternet: UnknownHostException) {
                emit(HandleResponse.Error(provideResources.string(R.string.no_connection)))
            } catch (e: Exception) {
                emit(HandleResponse.Error(provideResources.string(R.string.unexpected_error)))
            }
        }


    }
}