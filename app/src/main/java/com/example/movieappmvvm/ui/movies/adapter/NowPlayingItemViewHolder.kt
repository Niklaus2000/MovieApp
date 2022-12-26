package com.example.movieappmvvm.ui.movies.adapter

import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemNowPlayingBinding
import com.example.movieappmvvm.ui.core.OnItemClick

class NowPlayingItemViewHolder(
    private val binding: ItemNowPlayingBinding ,
    itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseItemHomeViewHolder(binding, itemClickListener) {

    override fun bind(item: MoviesUIModel) = with(binding) {
        item.bindNowPlayingItem(binding)
        super.bind(item)
    }
}