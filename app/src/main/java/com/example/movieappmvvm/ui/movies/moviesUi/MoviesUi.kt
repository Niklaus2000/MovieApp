package com.example.movieappmvvm.ui.movies.moviesUi

import android.view.View
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentMoviesBinding
import com.example.movieappmvvm.ui.movies.adapter.HomeViewAdapter

interface MoviesUi {
    fun apply(binding: FragmentMoviesBinding , adapter: HomeViewAdapter)

    object Empty : MoviesUi {
        override fun apply(binding: FragmentMoviesBinding , adapter: HomeViewAdapter ) = Unit
    }
    class LoadingUi : MoviesUi {
        override fun apply(
            binding: FragmentMoviesBinding ,
            adapter: HomeViewAdapter ,
            ) {
            binding.progressBar.visibility = View.VISIBLE

        }
    }
    class ContentUi(private val items: List<MoviesUIModel>) : MoviesUi {
        override fun apply(
            binding: FragmentMoviesBinding ,
            adapter: HomeViewAdapter ,
        ) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(items)
        }
    }

    class ErrorUi(private val errorMessage: String) : MoviesUi {
        override fun apply(
            binding: FragmentMoviesBinding ,
            adapter: HomeViewAdapter ,
        ) = with(binding) {
            progressBar.visibility = View.GONE

        }
    }


}