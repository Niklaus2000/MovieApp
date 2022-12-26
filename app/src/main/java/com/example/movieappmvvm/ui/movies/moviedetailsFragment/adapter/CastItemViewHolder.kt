package com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter

import com.example.movieappmvvm.data.model.moviesUiModel.CastUIModel
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemCastBinding
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.BaseItemHomeViewHolder

class CastItemViewHolder(
    private val binding: ItemCastBinding
) : BaseItemCastViewHolder(binding) {

    override fun bind(item: CastUIModel) = with(binding) {
       // item.bindHomeMovieItem(binding)
        item.bindDetailMoviesItem(binding)
        super.bind(item)
    }
}