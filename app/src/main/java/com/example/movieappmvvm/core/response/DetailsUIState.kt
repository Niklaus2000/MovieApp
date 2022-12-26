package com.example.movieappmvvm.core.response

import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel


sealed class DetailsUIState  {
    object Loading : DetailsUIState()
    class Success(val data: MoviesUIModel): DetailsUIState()
    class Error(val message: String): DetailsUIState()


}