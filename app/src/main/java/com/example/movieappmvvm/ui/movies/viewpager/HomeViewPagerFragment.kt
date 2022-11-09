package com.example.movieappmvvm.ui.movies.viewpager

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.load
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.databinding.FragmentHomeViewPagerBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeViewPagerFragment(val movie: Movie) : BaseFragment<HomeViewPagerFragmentViewModel,FragmentHomeViewPagerBinding>(FragmentHomeViewPagerBinding::inflate) {

    override val viewModel: HomeViewPagerFragmentViewModel by viewModels()

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        setUpViewPager()
    }


    private fun setUpViewPager() {
        binding.viewPagerImage.load(CONSTANTS.ImageBaseURL + movie.backdrop_path) {
            placeholder(CONSTANTS.viewPagerPlaceHolder.random())
            error(CONSTANTS.viewPagerPlaceHolder.random())
        }
        binding.viewPagerText.text = movie.title

        binding.root.setOnClickListener {
            val bundle = bundleOf(CONSTANTS.movie to movie)
            it.findNavController().navigate(R.id.movieDetailsFragment, bundle)
        }

    }


}