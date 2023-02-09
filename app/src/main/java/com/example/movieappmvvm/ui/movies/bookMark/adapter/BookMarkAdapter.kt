package com.example.movieappmvvm.ui.movies.bookMark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemNowPlayingBinding
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.NowPlayingItemViewHolder

class BookMarkAdapter(
    private val itemClickListener: OnItemClick<MovieDB> ,
) : BaseRecyclerViewAdapter<MovieDB>() {
    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int ,
    ): BaseViewHolder<MovieDB> =
        BookMarkItemViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            ) , itemClickListener
        )
}