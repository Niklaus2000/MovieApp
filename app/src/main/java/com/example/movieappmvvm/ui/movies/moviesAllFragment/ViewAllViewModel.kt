package com.example.movieappmvvm.ui.movies.moviesAllFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieappmvvm.core.Dispatchers
import com.example.movieappmvvm.core.response.UiState
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.repository.viewAllRepository.ViewAllRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val repository: ViewAllRepository ,
    private val dispatchers: Dispatchers ,
) : ViewModel() {

    //
    private val popularStateFlow = MutableStateFlow<UiState>(UiState.Loading)
    val _popularStateFlow = popularStateFlow.asStateFlow()

//    private val popularLiveData = MutableLiveData<PagingData<Movie>>()
//    val _popularLiveData: LiveData<PagingData<Movie>> = popularLiveData


//    fun getPopular () {
//        dispatchers.launchBackground(viewModelScope) {
//            repository.getPopularMovieResult()
//
//        }
//    }

//    private val upComingStateFlow = MutableStateFlow<UiState>(UiState.Loading)
//    val _upComingStateFlow: StateFlow<UiState> = upComingStateFlow
//
//    private val topRatedStateFlow = MutableStateFlow<UiState>(UiState.Loading)
//    val _topRatedStateFlow: StateFlow<UiState> = topRatedStateFlow


    fun getPopular() {
        dispatchers.launchBackground(viewModelScope) {
            repository.getPopularMovieResult()
                .catch {
                    popularStateFlow.value = UiState.Error(it.message.toString())
                }
                .collectLatest { response ->
                    popularStateFlow.value = UiState.Success(data = response)

                }

        }
    }
}


//    fun getTopRated() {
//        dispatchers.launchBackground(viewModelScope) {
//            repository.getPopularMovieResult()
//                .catch {
//                    topRatedStateFlow.value = UiState.Error(it.message.toString())
//                }
//                .collectLatest { response ->
//                    topRatedStateFlow.value = UiState.Success(data = response)
//                }
//        }
//    }
//}


//
//        dispatchers.launchBackground(viewModelScope) {
//            moviesRepository.getUpcomingMovie().collectLatest { it ->
//                val moviesData = when (it) {
//                    is HandleResponse.Loading -> MoviesUi.LoadingUi()
//                    is HandleResponse.Success -> MoviesUi.ContentUi(it.data.results.map {
//                        MoviesUIModel(
//                            it.poster_path,
//                            it.title,
//                            it.vote_average.toString()
//                        )
//                    })
//                    is HandleResponse.Error -> MoviesUi.ErrorUi(it.message)
//
//                }
//                _listOfMoviesUpComingState.value = moviesData
//            }
//        }


//    fun fetchPopular() = repository.getPopularMovieResult().cachedIn(viewModelScope)
//
//    fun fetchUpcoming() =
//        repository.getUpcomingMovieResult().cachedIn(viewModelScope)
//
//    fun fetchTopRated() =
//        repository.getTopRatedMovieResult().cachedIn(viewModelScope)

