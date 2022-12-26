package com.example.movieappmvvm.ui.movies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentMoviesBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.HomeViewAdapter
import com.example.movieappmvvm.ui.movies.adapter.OnGoingAdapter
import com.example.movieappmvvm.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment :
    BaseFragment<MoviesViewModel , FragmentMoviesBinding>(FragmentMoviesBinding::inflate), OnItemClick<MoviesUIModel> {
    override val viewModel: MoviesViewModel by viewModels()

    private lateinit var navController: NavController


    private lateinit var moviesPopularAdapter: HomeViewAdapter
    private lateinit var topRatedAdapter: HomeViewAdapter
    private lateinit var upComingAdapter: HomeViewAdapter
    private lateinit var onGoingAdapter: OnGoingAdapter

    override fun onViewCreated(view: View , savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view , savedInstanceState)

        searchButton.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }


        viewModel.moviesUpComing()
        viewModel.moviesPopular()
        viewModel.moviesTopRated()
        viewModel.moviesOnGoing()
        initAdapters()
        passInfoNextPage()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfMoviesPopularState.collect {
                it.apply(binding , moviesPopularAdapter)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfMoviesTopRatedState.collect {
                it.apply(binding , topRatedAdapter)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfMoviesUpComingState.collect {
                it.apply(binding , upComingAdapter)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfMoviesOnGoingState.collect {
                it.apply(binding , onGoingAdapter)
            }
        }



    }

    private fun initAdapters(): Unit = with(binding) {
        moviesPopularAdapter = HomeViewAdapter(this@MoviesFragment)
        recyclerViewPopular.adapter = moviesPopularAdapter
        recyclerViewPopular.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)



        topRatedAdapter = HomeViewAdapter(this@MoviesFragment)
        recyclerViewTopRated.adapter = topRatedAdapter
        recyclerViewTopRated.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)

        upComingAdapter = HomeViewAdapter(this@MoviesFragment)
        recyclerViewUpcoming.adapter = upComingAdapter
        recyclerViewUpcoming.layoutManager =
            LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)

        onGoingAdapter = OnGoingAdapter(this@MoviesFragment)
        recyclerViewNowPlaying.adapter = onGoingAdapter
        recyclerViewNowPlaying.layoutManager =
            LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
    }

    private fun passInfoNextPage(): Unit = with(binding) {
        textViewAllPopular.setOnClickListener {
            findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToViewAllFragment(
                (CONSTANTS.Popular)))

        }
        textViewVllTopRated.setOnClickListener {
            findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToViewAllFragment(
                (CONSTANTS.TopRated)))

        }
        textViewAllUpcoming.setOnClickListener {
            findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToViewAllFragment(
                CONSTANTS.Upcoming))

        }
    }

    override fun onItemClick(item: MoviesUIModel) {
       findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(item.id))
    }




}
