package com.example.movieappmvvm.di.networkModule

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.movieappmvvm.BuildConfig
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.utils.CONSTANTS
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .connectTimeout(10 , TimeUnit.SECONDS)
        .readTimeout(10 , TimeUnit.SECONDS)
        .writeTimeout(10 , TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CONSTANTS.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieAppService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

//
//    val loggerInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//
//    val chuckerInterceptor = ChuckerInterceptor.Builder(applicationContext)
//        .collector(ChuckerCollector(applicationContext))
//        .build()
//
//    val okHttpClient = OkHttpClient.Builder().apply {
//        val isDebug: Boolean = BuildConfig.DEBUG
//        if (isDebug) {
//            addInterceptor(loggerInterceptor)
//        }
//        addInterceptor(chuckerInterceptor)
//    }.build()
//
//    val retrofit = Retrofit.Builder()
//        .baseUrl(CONSTANTS.BaseURL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttpClient)
//        .build()
//
//    return retrofit.create(::
//    class.java)


//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor ,
//    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
//        .callTimeout(15 , TimeUnit.SECONDS)
//        .writeTimeout(15 , TimeUnit.SECONDS)
//        .readTimeout(15 , TimeUnit.SECONDS)
//        .connectTimeout(15 , TimeUnit.SECONDS)
//        .retryOnConnectionFailure(true).build()
//
//    @Provides
//    @Singleton
//    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
//        Retrofit.Builder().baseUrl(CONSTANTS.BaseURL).addConverterFactory(
//            MoshiConverterFactory.create(
//                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//            )
//        ).client(okHttpClient).build()
//
//
//    @Provides
//    @Singleton
//    fun provideRetrofitCurrency(retrofitClient: Retrofit): MoviesService =
//        retrofitClient.create(MoviesService::class.java)

//
//    @Provides
//    @Singleton
//    fun provideRetrofitClient(converterFactory: GsonConverterFactory): Retrofit {
//        return Retrofit.Builder().baseUrl(CONSTANTS.BaseURL)
//            .addConverterFactory(converterFactory)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideGenresService(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }


}


//    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
//            .readTimeout(15, TimeUnit.SECONDS)
//            .build()
//    }
