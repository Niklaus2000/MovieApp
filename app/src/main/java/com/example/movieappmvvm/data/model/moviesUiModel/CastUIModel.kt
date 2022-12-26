package com.example.movieappmvvm.data.model.moviesUiModel

import coil.load
import com.example.movieappmvvm.databinding.ItemCastBinding
import com.example.movieappmvvm.utils.CONSTANTS

data class CastUIModel(
    val id: Int? ,
    val castName: String? ,
    val image: String? ,
) {
    fun bindDetailMoviesItem(binding: ItemCastBinding): Unit = with(binding) {
        castImageView.load(CONSTANTS.ImageBaseURL + image)
        castNameTextView.text = castName

    }
}
