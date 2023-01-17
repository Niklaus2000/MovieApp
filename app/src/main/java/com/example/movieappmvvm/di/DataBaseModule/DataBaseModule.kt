package com.example.movieappmvvm.di.DataBaseModule

import android.content.Context
import androidx.room.Room
import com.example.movieappmvvm.data.cache.MovieDao
import com.example.movieappmvvm.data.cache.MovieDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBaseModule {


    companion object {

        @Provides
        @Singleton
        fun provideArticleDatabase(@ApplicationContext context: Context): MovieDatabase {
            return Room.databaseBuilder(context , MovieDatabase::class.java , "movies_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }


        @Provides
        @Singleton
        fun provideArticleDao(articleDatabase: MovieDatabase): MovieDao =
            articleDatabase.movieDao()

    }
}
