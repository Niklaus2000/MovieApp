package com.example.movieappmvvm.ui.movies.bookMark.adapter

import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemNowPlayingBinding
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.BaseItemHomeViewHolder

class BookMarkItemViewHolder(
    private val binding: ItemSearchBinding ,
    itemClickListener: OnItemClick<MovieDB> ,
) : BaseItemViewBookMarkViewHolder(binding, itemClickListener) {

    override fun bind(item: MovieDB) = with(binding) {
        item.bindBookMarkItem(binding)
        super.bind(item)
    }
}