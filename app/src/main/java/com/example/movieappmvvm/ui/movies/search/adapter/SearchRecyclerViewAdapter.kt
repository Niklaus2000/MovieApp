package com.example.movieappmvvm.ui.movies.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.databinding.ItemSearchBinding
import com.example.movieappmvvm.utils.CONSTANTS

@ExperimentalStdlibApi
class SearchRecyclerViewAdapter() :
    PagingDataAdapter<Movie , SearchRecyclerViewAdapter.ViewHolder>(MOVIE_COMPARATOR) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSearchBinding.bind(itemView)


        fun bind(movie: Movie) {
            binding.apply {
                searchImage.load(CONSTANTS.ImageBaseURL + movie.poster_path) {
                    placeholder(CONSTANTS.moviePlaceHolder[position % 4])
                    error(CONSTANTS.moviePlaceHolder[position % 4])
                }
                itemView.setOnClickListener {
                    val bundle = bundleOf(CONSTANTS.movie to movie)
                    it.findNavController().navigate(R.id.movieDetailsFragment , bundle)

                }
            }
        }
    }

    override fun onBindViewHolder(holder:ViewHolder , position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int ,
    ): SearchRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search , parent , false))
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie , newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie , newItem: Movie): Boolean =
                oldItem == newItem

        }
    }
}


