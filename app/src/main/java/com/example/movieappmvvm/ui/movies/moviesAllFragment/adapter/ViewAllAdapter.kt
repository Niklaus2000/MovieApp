package com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.base.BaseRecyclerViewPageAdapter
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.HomeItemViewHolder
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.ExperimentalCoroutinesApi

//@ExperimentalCoroutinesApi
//class ViewAllAdapter : PagingDataAdapter<Movie , ViewAllAdapter.ViewHolder>(MOVIE_COMPARATOR) {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val binding = ItemSearchBinding.bind(itemView)
//
//        fun bind(movie: Movie) {
//            binding.apply {
//                Glide.with(itemView)
//                    .load(CONSTANTS.ImageBaseURL + movie.poster_path)
//                    .placeholder(CONSTANTS.moviePlaceHolder[position % 4])
//                    .error(CONSTANTS.moviePlaceHolder[position % 4])
//                    .into(searchImage)
//
//                itemView.setOnClickListener {
//                    val bundle = bundleOf(CONSTANTS.movie to movie)
//                    it.findNavController().navigate(R.id.movieDetailsFragment , bundle)
//                }
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_search , parent , false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
//
//        val currentItem = getItem(position)
//        if (currentItem != null) {
//            holder.bind(currentItem)
//        }
//
//    }
//
//    companion object {
//        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
//            override fun areItemsTheSame(oldItem: Movie , newItem: Movie): Boolean =
//                oldItem.id == newItem.id
//
//            override fun areContentsTheSame(oldItem: Movie , newItem: Movie): Boolean =
//                oldItem == newItem
//
//        }
//    }
//
//
//}

class ViewAllAdapter(
    private val itemClickListener: OnItemClick<MoviesUIModel>,
) : BaseRecyclerViewPageAdapter<MoviesUIModel>() {


    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): BaseViewHolder<MoviesUIModel> =
        ViewAllItemViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            ),itemClickListener
        )




}