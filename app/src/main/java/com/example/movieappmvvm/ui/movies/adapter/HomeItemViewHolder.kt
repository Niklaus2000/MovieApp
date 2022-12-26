package com.example.movieappmvvm.ui.movies.adapter

import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.ui.core.OnItemClick


class HomeItemViewHolder(
    private val binding: ItemMovieHomeBinding ,
    itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseItemHomeViewHolder(binding, itemClickListener) {

    override fun bind(item: MoviesUIModel) = with(binding) {
        item.bindHomeMovieItem(binding)
        super.bind(item)
    }
}