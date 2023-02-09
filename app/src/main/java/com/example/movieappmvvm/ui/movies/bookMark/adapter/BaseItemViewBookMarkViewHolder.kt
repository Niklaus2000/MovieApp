package com.example.movieappmvvm.ui.movies.bookMark.adapter

import androidx.viewbinding.ViewBinding
import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.core.OnItemClick

abstract class BaseItemViewBookMarkViewHolder(
    binding: ViewBinding ,
    private val itemClickListener: OnItemClick<MovieDB> ,
) : BaseRecyclerViewAdapter.BaseViewHolder<MovieDB>(binding.root) {

    override fun bind(item: MovieDB) {
        onClick { itemClickListener.onItemClick(item) }
    }
}
