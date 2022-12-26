package com.example.movieappmvvm.ui.movies.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.ui.base.BaseRecyclerViewPageAdapter
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter.ViewAllItemViewHolder

class SearchAdapter(
    private val itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseRecyclerViewPageAdapter<MoviesUIModel>() {


    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): BaseViewHolder<MoviesUIModel> =
        SearchItemViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            ),itemClickListener
        )
}