package com.example.movieappmvvm.ui.movies.moviesUi

import android.view.View
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentMoviesBinding
import com.example.movieappmvvm.ui.movies.adapter.OnGoingAdapter


interface MoviesAllUi {
    fun apply(binding: FragmentMoviesBinding , adapter: OnGoingAdapter)

    object Empty : MoviesAllUi {
        override fun apply(binding: FragmentMoviesBinding, adapter: OnGoingAdapter) = Unit
    }

    class LoadingUi : MoviesAllUi {
        override fun apply(
            binding: FragmentMoviesBinding ,
            adapter: OnGoingAdapter ,
            ) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    class ContentUi(private val items: List<MoviesUIModel>) : MoviesAllUi {
        override fun apply(
            binding: FragmentMoviesBinding ,
            adapter: OnGoingAdapter
            ) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(items)

        }
    }

    class ErrorUi(private val errorMessage: String) : MoviesAllUi {
        override fun apply(
            binding: FragmentMoviesBinding ,
            adapter: OnGoingAdapter,
            ) {
            binding.progressBar.visibility = View.GONE

        }
    }
}