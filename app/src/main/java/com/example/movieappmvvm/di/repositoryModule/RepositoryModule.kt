package com.example.movieappmvvm.di.repositoryModule

import com.example.movieappmvvm.core.HandleApiRequest
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.cache.MovieDao
import com.example.movieappmvvm.data.repository.bookMarkRepository.MovieBookMarkRepository
import com.example.movieappmvvm.data.repository.bookMarkRepository.MovieBookMarkRepositoryImpl
import com.example.movieappmvvm.data.repository.movieDetailsRepository.MovieDetailsRepository
import com.example.movieappmvvm.data.repository.movieDetailsRepository.MovieDetailsRepositoryImpl
import com.example.movieappmvvm.data.repository.moviesRepository.MoviesRepository
import com.example.movieappmvvm.data.repository.moviesRepository.MoviesRepositoryImpl
import com.example.movieappmvvm.data.repository.searchRepository.SearchRepository
import com.example.movieappmvvm.data.repository.searchRepository.SearchRepositoryImpl
import com.example.movieappmvvm.data.repository.viewAllRepository.ViewAllRepository
import com.example.movieappmvvm.data.repository.viewAllRepository.ViewAllRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(
        apiService: MoviesService,
        handleApiRequest: HandleApiRequest
    ): MoviesRepository = MoviesRepositoryImpl(apiService, handleApiRequest)

    @Provides
    fun provideMoviesAllRepository(
        apiService: MoviesService
    ): ViewAllRepository = ViewAllRepositoryImpl(apiService)

   @Provides
   fun provideSearchRepository(
       apiService: MoviesService
   ): SearchRepository = SearchRepositoryImpl(apiService)

    @Provides
    fun provideMovieDetailsRepository(
        apiService: MoviesService,
        handleApiRequest: HandleApiRequest,
        movieDao : MovieDao
    ): MovieDetailsRepository = MovieDetailsRepositoryImpl(handleApiRequest, apiService, movieDao)

    @Provides
    fun provideMovieBookMarkRepository(
        movieDao: MovieDao
    ): MovieBookMarkRepository = MovieBookMarkRepositoryImpl(movieDao)

}

