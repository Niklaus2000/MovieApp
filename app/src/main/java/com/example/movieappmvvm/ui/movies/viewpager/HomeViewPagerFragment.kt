package com.example.movieappmvvm.ui.movies.viewpager

import androidx.core.os.bundleOf
import coil.load
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.databinding.FragmentHomeViewPagerBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeViewPagerFragment(val movie: Movie) : BaseFragmentBinding<FragmentHomeViewPagerBinding>(FragmentHomeViewPagerBinding::inflate) {

    override fun start() {
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
            //it.findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)
        }

    }


}