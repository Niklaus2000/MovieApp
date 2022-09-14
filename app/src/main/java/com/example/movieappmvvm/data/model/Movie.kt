package com.example.movieappmvvm.data.model

import android.os.Parcelable
import android.view.View
import coil.load
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movie (
    val id: Int,
    val vote_count: Int ?= null,
    val runtime: Int ?= null,
    val poster_path: String ?= null,
    val overview: String ?= null,
    val release_date: String ?= null,
    val title: String ?= null,
    val backdrop_path: String ?= null,
    val popularity: Number ?= null,
    val vote_average: Number ?= null,
    val genre_ids: List<Int> ?= null,
    val genre_names: List<String> ?= null,
    val genres: @RawValue List<Genre> ?= null,
    val video : Boolean ?= null,
    val videos: @RawValue Video ?= null
) : Parcelable {


    fun bind(binding: ItemMovieHomeBinding , position: Int  , movies : ArrayList<Movie>) {
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

//        itemView.setOnClickListener {
//            val bundle = bundleOf(CONSTANTS.movie to movies[position])
//            it.findNavController().navigate(R.id.movieDetailsFragment , bundle)
//        }

        if (position == movies.size - 1) {
            binding.spacingEnd.visibility = View.VISIBLE
        }



    }
}

