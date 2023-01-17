package com.example.movieappmvvm.ui.movies.moviedetailsFragment

import android.util.Log.e
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.core.Dispatchers
import com.example.movieappmvvm.core.response.DetailsUIState
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.CastUIModel
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.data.repository.movieDetailsRepository.MovieDetailsRepository
import com.example.movieappmvvm.ui.movies.moviesUi.CastUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@Suppress("IMPLICIT_CAST_TO_ANY")
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val dispatchers: Dispatchers ,
    private val repository: MovieDetailsRepository ,
) : ViewModel() {


    private val _moviesOfDetailsState = MutableStateFlow<DetailsUIState>(DetailsUIState.Loading)
    val moviesOfDetailsState = _moviesOfDetailsState.asStateFlow()


    private val _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    private val _bookMarkMovie = MutableLiveData(false)
    val bookMarkMovie: LiveData<Boolean> = _bookMarkMovie


    private val _listOfCast = MutableStateFlow<CastUi>(CastUi.Empty)
    val listOfCast = _listOfCast.asStateFlow()


    fun bookmarkMovie() {
        movie.value!!.run { val movieDb = MovieDB(
                id,
                poster_path!!,
                overview!!,
                title!!,
                backdrop_path!!)
            viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                if (_bookMarkMovie.value == true) {
                    repository.removeMovie(movieDb)
                } else {
                    repository.insertMovie(movieDb)
                }
            }
            e("Bookmark" , "Error")
        }
    }

//    fun checkBookmarkExist() {
//        dispatchers.launchBackground(viewModelScope){
//            if(movie!= null) {
//               return@launchBackground  _bookMarkMovie.postValue(repository.bookmarkExist(movie.value!!.id))
//            }else {
//               return@launchBackground
//            }
//
//        }
//    }

    fun checkBookmarkExist() {
        dispatchers.launchBackground(viewModelScope) {
            val response = repository.
            bookmarkExist(
                movie.value!!.id
            )
            _bookMarkMovie.postValue(response)
        }
    }


    fun getMoviesDetails(movie_id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            repository.getMoviesDetails(movie_id).collectLatest { it ->
                val detailsData = when (it) {
                    is HandleResponse.Loading -> DetailsUIState.Loading
                    is HandleResponse.Success -> DetailsUIState.Success(MoviesUIModel(
                        it.data.id ,
                        it.data.poster_path ,
                        it.data.title ,
                        it.data.vote_average!!.toFloat() ,
                        it.data.release_date ,
                        it.data.runtime ,
                        it.data.genres!! ,
                        it.data.overview ,
                        it.data.backdrop_path ,

                        ))
                    is HandleResponse.Error -> DetailsUIState.Error(it.message)
                }
                _moviesOfDetailsState.value = detailsData

            }
        }
    }


    fun getCast(movie_id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            repository.loadCast(movie_id).collectLatest {
                val castData = when (it) {
                    is HandleResponse.Loading -> CastUi.LoadingUi()
                    is HandleResponse.Success -> CastUi.ContentUi(it.data.cast!!.map { it ->
                        CastUIModel(
                            it.id ,
                            it.name ,
                            it.profile_path ,
                        )
                    })
                    is HandleResponse.Error -> CastUi.ErrorUi(it.message)
                }
                _listOfCast.value = castData
            }
        }
    }
}

