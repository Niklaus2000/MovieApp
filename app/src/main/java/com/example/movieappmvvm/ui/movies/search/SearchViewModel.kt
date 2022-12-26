package com.example.movieappmvvm.ui.movies.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieappmvvm.core.Dispatchers
import com.example.movieappmvvm.core.response.UiState
import com.example.movieappmvvm.data.repository.searchRepository.SearchRepository
//import com.example.movieappmvvm.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val repository: SearchRepository
) : ViewModel() {

////    private val searchStateFlow = MutableStateFlow<UiState>(UiState.Loading)
////    val _searchStateFlow = searchStateFlow.asStateFlow()
//
//
//    private val searchStateFlow = MutableStateFlow<UiState>(UiState.Loading)
//    val _searchStateFlow = searchStateFlow.asStateFlow()
//
//    fun getSearch(query: String) {
//        dispatchers.launchUI(viewModelScope){
//            repository.getSearchResult(query)
//                .catch {
//                    searchStateFlow.value = UiState.Error(it.message.toString())
//                }
//                .collectLatest { response ->
//                    searchStateFlow.value = UiState.Success(data = response)
//
//                }
//
//        }
//
//    }



      fun getSearch(query: String) = repository.getSearchResult(query).cachedIn(viewModelScope)





}