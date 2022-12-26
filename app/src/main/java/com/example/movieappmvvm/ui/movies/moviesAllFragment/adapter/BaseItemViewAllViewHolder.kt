package com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter

import androidx.viewbinding.ViewBinding
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.base.BaseRecyclerViewPageAdapter
import com.example.movieappmvvm.ui.core.OnItemClick

abstract class BaseItemViewAllViewHolder(
    binding: ViewBinding ,
    private val itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseRecyclerViewPageAdapter.BaseViewHolder<MoviesUIModel>(binding.root) {


    override fun bind(item: MoviesUIModel) {
        onClick { itemClickListener.onItemClick(item) }
    }
}