package com.example.movieappmvvm.module

import com.example.movieappmvvm.data.api.NetworkService
import com.example.movieappmvvm.utils.CONSTANTS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    operator fun invoke() : NetworkService {
        return Retrofit.Builder()
            .baseUrl(CONSTANTS.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
}

