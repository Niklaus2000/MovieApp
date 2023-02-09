package com.example.movieappmvvm.data.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.movieappmvvm.data.model.MovieDB
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMovie(Movie: MovieDB)

     @Query("SELECT * FROM MovieDB")
     fun getAllMovies(): Flow<List<MovieDB>>

     @Delete
     fun removeMovie(movie: MovieDB)

    @Query("SELECT EXISTS (SELECT 1 FROM MovieDB WHERE id = :id) ")
    fun bookmarkExist(id: Int): Boolean

    @Query("SELECT * FROM MovieDB WHERE title LIKE :query || '%'")
    fun searchMovie(query: String): Flow<List<MovieDB>>


}