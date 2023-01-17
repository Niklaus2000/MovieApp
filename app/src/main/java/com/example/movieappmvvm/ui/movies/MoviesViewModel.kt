package com.example.movieappmvvm.ui.movies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.core.Dispatchers
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.data.repository.moviesRepository.MoviesRepository
import com.example.movieappmvvm.ui.movies.moviesUi.MoviesAllUi
import com.example.movieappmvvm.ui.movies.moviesUi.MoviesUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository ,
    private val dispatchers: Dispatchers,
) : ViewModel() {




    private val _listOfMoviesUpComingState = MutableStateFlow<MoviesUi>(MoviesUi.Empty)
    val listOfMoviesUpComingState = _listOfMoviesUpComingState.asStateFlow()

    private val _listOfMoviesPopularState = MutableStateFlow<MoviesUi>(MoviesUi.Empty)
    val listOfMoviesPopularState = _listOfMoviesPopularState.asStateFlow()

    private val _listOfMoviesTopRatedState = MutableStateFlow<MoviesUi>(MoviesUi.Empty)
    val listOfMoviesTopRatedState = _listOfMoviesTopRatedState.asStateFlow()

    private val _listOfMoviesOnGoingState = MutableStateFlow<MoviesAllUi>(MoviesAllUi.Empty)
    val listOfMoviesOnGoingState = _listOfMoviesOnGoingState.asStateFlow()

    fun moviesUpComing() {
            dispatchers.launchBackground(viewModelScope) {
                moviesRepository.getUpcomingMovie().collectLatest { it ->
                    val moviesData = when (it) {
                        is HandleResponse.Loading -> MoviesUi.LoadingUi()
                        is HandleResponse.Success -> MoviesUi.ContentUi(it.data.results.map {
                            MoviesUIModel(
                                it!!.id!! ,
                                it.poster_path!!,
                                it.title!!,
                                it.vote_average!!.toFloat(),
                                it.release_date!! ,
                                it.runtime,
                                it.genres ,
                                it.overview!!,
                                it.backdrop_path ,



                            ) })
                        is HandleResponse.Error -> MoviesUi.ErrorUi(it.message)

                    }
                    _listOfMoviesUpComingState.value = moviesData
                }
            }
    }

    fun moviesPopular() {
        dispatchers.launchBackground(viewModelScope) {
            moviesRepository.getPopularMovie().collectLatest { it ->
                val moviesData = when (it) {
                    is HandleResponse.Loading -> MoviesUi.LoadingUi()
                    is HandleResponse.Success -> MoviesUi.ContentUi(it.data.results.map {
                        MoviesUIModel(
                            it!!.id!! ,
                            it.poster_path!!,
                            it.title!!,
                            it.vote_average!!.toFloat(),
                            it.release_date!! ,
                            it.runtime,
                            it.genres,
                            it.overview!!,
                            it.backdrop_path,
                            ) })
                    is HandleResponse.Error -> MoviesUi.ErrorUi(it.message)
                }
                _listOfMoviesPopularState.value = moviesData
            }
        }
    }


    fun moviesTopRated() {
        dispatchers.launchBackground(viewModelScope) {
            moviesRepository.getTopRatedMovie().collectLatest { it ->
                val moviesData = when (it) {
                    is HandleResponse.Loading -> MoviesUi.LoadingUi()
                    is HandleResponse.Success -> MoviesUi.ContentUi(it.data.results.map {
                        MoviesUIModel(
                            it!!.id!!,
                            it.poster_path!!,
                            it.title!!,
                            it.vote_average!!.toFloat(),
                            it.release_date!!,
                            it.runtime,
                            it.genres ,
                            it.overview!!,
                            it.backdrop_path!!,
                        ) })
                    is HandleResponse.Error -> MoviesUi.ErrorUi(it.message)

                }
                _listOfMoviesTopRatedState.value = moviesData
            }
        }
    }
    fun moviesOnGoing() {
        dispatchers.launchBackground(viewModelScope) {
            moviesRepository.getNowPlayingMovie().collectLatest { it ->
                val moviesData = when (it) {
                    is HandleResponse.Loading -> MoviesAllUi.LoadingUi()
                    is HandleResponse.Success -> MoviesAllUi.ContentUi(it.data.results.map {
                        MoviesUIModel(
                            it!!.id,
                            it.poster_path!!,
                            it.title!!,
                            it.vote_average!!.toFloat(),
                            it.release_date!!,
                            it.runtime,
                            it.genres,
                            it.overview!!,
                            it.backdrop_path!!,
                        ) })
                    is HandleResponse.Error -> MoviesAllUi.ErrorUi(it.message)

                }
                _listOfMoviesOnGoingState.value = moviesData
            }
        }
    }

}