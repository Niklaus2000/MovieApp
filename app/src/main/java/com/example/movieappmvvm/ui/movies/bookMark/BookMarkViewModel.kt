package com.example.movieappmvvm.ui.movies.bookMark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.core.Dispatchers
import com.example.movieappmvvm.core.response.BookMarkState
import com.example.movieappmvvm.data.repository.bookMarkRepository.MovieBookMarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val repository: MovieBookMarkRepository
)  : ViewModel() {


    private val _bookMarkState = MutableStateFlow<BookMarkState>(BookMarkState.Success(emptyList()))
    val bookMarkState: StateFlow<BookMarkState> = _bookMarkState



    fun fetchBookMark() {
        dispatchers.launchBackground(viewModelScope) {
         repository.getAllBookMark()
             .catch {
                 _bookMarkState.value = BookMarkState.Error(it.message.toString())
             }
             .collect{
                 _bookMarkState.value = BookMarkState.Success(it)
             }
        }
    }
    fun searchMovie(movie_id: String) {
        dispatchers.launchBackground(viewModelScope) {
            repository.searchMovie(movie_id)
                .collect{
                    _bookMarkState.value = BookMarkState.Success(it)
                }

        }
    }

}




