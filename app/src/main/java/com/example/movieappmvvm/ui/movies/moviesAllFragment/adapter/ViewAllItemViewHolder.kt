package com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter

import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.BaseItemHomeViewHolder

class ViewAllItemViewHolder(
    private val binding: ItemSearchBinding ,
    itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseItemViewAllViewHolder(binding, itemClickListener) {

    override fun bind(item: MoviesUIModel) = with(binding) {
        item.bindAllMovieItem(binding)
        super.bind(item)
    }
}