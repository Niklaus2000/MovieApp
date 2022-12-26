package com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter

import androidx.viewbinding.ViewBinding
import com.example.movieappmvvm.data.model.moviesUiModel.CastUIModel
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.core.OnItemClick

abstract class BaseItemCastViewHolder(
    binding: ViewBinding ,
) : BaseRecyclerViewAdapter.BaseViewHolder<CastUIModel>(binding.root) {
    override fun bind(item: CastUIModel) {
    }
}