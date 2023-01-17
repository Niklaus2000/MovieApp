package com.example.movieappmvvm.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappmvvm.data.model.MovieDB


@Database(entities = [MovieDB::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
abstract fun movieDao(): MovieDao
}