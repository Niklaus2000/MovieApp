package com.example.movieappmvvm.ui.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeRecyclerViewAdapter(val context : Context , val movies : ArrayList<Movie>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovieHomeBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_movie_home , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {

        with(holder) {

            when (position) {
                0 -> {
                    binding.spacingStart.visibility = View.VISIBLE
                }
                movies.size - 1 -> {
                    binding.spacingEnd.visibility = View.VISIBLE
                }
                else -> {
                    binding.spacingEnd.visibility = View.GONE
                    binding.spacingStart.visibility = View.GONE
                }
            }

            binding.movieImage.load(CONSTANTS.ImageBaseURL + movies[position].poster_path) {
                placeholder(CONSTANTS.moviePlaceHolder[position % 4])
                error(CONSTANTS.moviePlaceHolder[position % 4])
            }
            binding.textMovieName.text = movies[position].title
            binding.textMovieRating.text = movies[position].vote_average.toString()

            itemView.setOnClickListener {
                val bundle = bundleOf(CONSTANTS.movie to movies[position])
                it.findNavController().navigate(R.id.movieDetailsFragment , bundle)
            }

            if (position == movies.size - 1) {
                binding.spacingEnd.visibility = View.VISIBLE
            }
        }

    }

    override fun getItemCount() = movies.size


}



