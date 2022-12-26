package com.example.movieappmvvm.ui.movies.moviesUi

import android.view.View
import com.example.movieappmvvm.data.model.moviesUiModel.CastUIModel
import com.example.movieappmvvm.databinding.FragmentMovieDetailsBinding
import com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter.CastRecyclerViewAdapter


interface CastUi {
    fun apply(binding: FragmentMovieDetailsBinding, adapter: CastRecyclerViewAdapter)

    object Empty : CastUi {
        override fun apply(binding: FragmentMovieDetailsBinding, adapter: CastRecyclerViewAdapter) = Unit
    }

    class LoadingUi : CastUi {
        override fun apply(
            binding: FragmentMovieDetailsBinding ,
            adapter: CastRecyclerViewAdapter
            ) {
        }
    }

    class ContentUi(private val items: List<CastUIModel>) : CastUi {
        override fun apply(
            binding: FragmentMovieDetailsBinding ,
            adapter: CastRecyclerViewAdapter
            ) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(items)

        }
    }

    class ErrorUi(private val errorMessage: String) : CastUi {
        override fun apply(
            binding: FragmentMovieDetailsBinding ,
            adapter: CastRecyclerViewAdapter
        ) = with(binding) {
            progressBar.visibility = View.GONE
        }
    }
}