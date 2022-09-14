package com.example.movieappmvvm.ui.movies.moviedetailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.Resource
import com.example.movieappmvvm.data.model.VideoResponse
import com.example.movieappmvvm.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
@ExperimentalStdlibApi
class MovieDetailsViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {


    private val _name = MutableLiveData("Movie Name")
    private val _movie = MutableLiveData<Movie>()
    private val _videos = MutableLiveData<VideoResponse>()

    var movieName: MutableLiveData<String> = _name
    var movie: MutableLiveData<Movie> = _movie
    var videos: MutableLiveData<VideoResponse> = _videos

    fun getMoviesDetails(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = networkRepository.getMovieDetails(movie_id)
            movie.postValue(apiResponse)

        }
    }
    fun loadCast(movie_id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            val apiResponse = networkRepository.getMovieCredits(movie_id)
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }
    fun getVideos(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = networkRepository.getVideos(movie_id)
                videos.postValue(apiResponse)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }
}