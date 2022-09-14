package com.example.movieappmvvm.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieappmvvm.data.model.Resource
import com.example.movieappmvvm.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.net.SocketTimeoutException
import javax.inject.Inject

//@HiltViewModel

@HiltViewModel
@ExperimentalCoroutinesApi
class MoviesViewModel @Inject constructor(private val repository : NetworkRepository) : ViewModel() {
//    private val _movies = MutableLiveData<MutableList<Movie>>()
//    val movies: MutableLiveData<MutableList<Movie>> get() = _movies

    fun loadNowPlaying() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            // Fetch data from remote
            val apiResponse = repository.getNowPlayingMovie()
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }
  


    fun loadUpcoming() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            // Fetch data from remote
            val apiResponse = repository.getUpcomingMovie()
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }

    fun loadPopular() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            // Fetch data from remote
            val apiResponse = repository.getPopularMovie()
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }

    fun loadTopRated() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            // Fetch data from remote
            val apiResponse = repository.getTopRatedMovie()
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }
}