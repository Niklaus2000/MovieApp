package com.example.movieappmvvm.ui.movies.moviesAllFragment

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentViewAllBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter.ViewAllRecyclerViewAdapter
import com.example.movieappmvvm.utils.CONSTANTS
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ViewAllFragment: BaseFragmentBinding<FragmentViewAllBinding>(FragmentViewAllBinding::inflate) {



    private lateinit var movieAdapter: ViewAllRecyclerViewAdapter
    private lateinit var movieSkeleton: Skeleton
    private val viewModel: ViewAllViewModel by viewModels()

    override fun start() {
        setUpSkeletonAndAdapter()


    }


    private fun setUpSkeletonAndAdapter() {
        movieAdapter = ViewAllRecyclerViewAdapter()
        binding.movieRecyclerView.adapter = movieAdapter

        movieSkeleton = binding.movieRecyclerView.applySkeleton(R.layout.item_search, 15)

        val pageType = requireArguments().get(CONSTANTS.viewAll)
        binding.pageTitle.text = pageType.toString()
        when(pageType) {
            CONSTANTS.Upcoming -> fetchUpcoming()
            CONSTANTS.TopRated -> fetchTopRated()
            CONSTANTS.Popular -> fetchPopular()
        }

        binding.buttonBack.setOnClickListener {
            binding.root.findNavController().navigateUp()
        }

    }

    private fun fetchPopular() {
        viewModel.fetchPopular().observe(viewLifecycleOwner) {

            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)

        }
    }

    private fun fetchTopRated() {

        viewModel.fetchTopRated().observe(viewLifecycleOwner) {

            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)

        }

    }

    private fun fetchUpcoming() {

        viewModel.fetchUpcoming().observe(viewLifecycleOwner) {

            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)

        }
    }
}