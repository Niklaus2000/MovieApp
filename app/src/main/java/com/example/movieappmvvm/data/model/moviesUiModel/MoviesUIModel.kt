package com.example.movieappmvvm.data.model.moviesUiModel

import coil.load
import com.example.movieappmvvm.data.model.Genre
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.databinding.ItemNowPlayingBinding
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.utils.CONSTANTS

data class MoviesUIModel(
    var id: Int ,
    val image: String? ,
    val title: String? ,
    val vote_average: Number? ,
    val release_date: String? ,
    val runtime: Int? ,
    val genres: List<Genre>? ,
    val overview: String? ,
    val bannerImage: String? ,

    ) {
    fun bindHomeMovieItem(
        binding: ItemMovieHomeBinding ,
    ) = with(binding) {
        imageView.load(CONSTANTS.ImageBaseURL + image)
        titleTextView.text = title
        movieRatingTextView.text = vote_average.toString()
    }
    fun bindAllMovieItem(binding: ItemSearchBinding) = with(binding) {
        searchImageView.load(CONSTANTS.ImageBaseURL + image)
    }
    fun bindNowPlayingItem(binding: ItemNowPlayingBinding) = with(binding ) {
        itemImageView.load(CONSTANTS.ImageBaseURL + image)
        itemTextView.text = title
    }
    fun bindSearchItem(binding: ItemSearchBinding) = with(binding) {
        searchImageView.load(CONSTANTS.ImageBaseURL + image)
    }
}

