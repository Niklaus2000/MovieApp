package com.example.movieappmvvm.ui.movies.search.adapter

import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter.BaseItemViewAllViewHolder

class SearchItemViewHolder(
    private val binding: ItemSearchBinding ,
    itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseItemSearchViewHolder(binding, itemClickListener) {

    override fun bind(item: MoviesUIModel) = with(binding) {
        item.bindSearchItem(binding)
        super.bind(item)
    }
}