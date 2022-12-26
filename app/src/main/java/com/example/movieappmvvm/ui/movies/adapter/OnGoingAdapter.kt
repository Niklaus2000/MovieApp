package com.example.movieappmvvm.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemNowPlayingBinding
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.core.OnItemClick

class OnGoingAdapter(
    private val itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseRecyclerViewAdapter<MoviesUIModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int ,
    ): BaseViewHolder<MoviesUIModel>  =
        NowPlayingItemViewHolder(
            ItemNowPlayingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClickListener
        )
}