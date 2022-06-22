package com.example.movieappmvvm.ui.movies

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.Status
import com.example.movieappmvvm.databinding.FragmentMoviesBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.ui.movies.adapter.HomeRecyclerViewAdapter
import com.example.movieappmvvm.ui.movies.adapter.HomeViewPagerAdapter
import com.example.movieappmvvm.utils.showToast
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesFragment : BaseFragmentBinding<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel : MoviesViewModel by viewModels()


    private var upcomingMovieList : ArrayList<Movie> = ArrayList()
    private var popularMovieList : ArrayList<Movie> = ArrayList()
    private var topRatedMovieList : ArrayList<Movie> = ArrayList()

    private lateinit var upcomingAdapter : HomeRecyclerViewAdapter
    private lateinit var popularAdapter : HomeRecyclerViewAdapter
    private lateinit var topRatedAdapter : HomeRecyclerViewAdapter

    private lateinit var viewPagerSkeleton : Skeleton
    lateinit var upcomingSkeleton : Skeleton
    lateinit var topRatedSkeleton : Skeleton
    lateinit var popularSkeleton : Skeleton





    override fun start() {
        fetchData()
        initAdapters()
        initSkeletons()

    }
    private fun fetchData() {
        viewModel.loadNowPlaying().observe(requireActivity(), Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                    viewPagerSkeleton.showSkeleton()
                }
                Status.SUCCESS -> {
                    viewPagerSkeleton.showOriginal()
                    binding.homeViewPager.adapter =
                        HomeViewPagerAdapter(childFragmentManager, lifecycle, res.data!!.results)
                }
                Status.ERROR -> {
                    showToast(res.msg.toString())
                }
            }
        })

        viewModel.loadUpcoming().observe(requireActivity(), Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                    if (upcomingMovieList.isNullOrEmpty())
                        upcomingSkeleton.showSkeleton()
                }
                Status.SUCCESS -> {
                    upcomingSkeleton.showOriginal()
                    upcomingMovieList.clear()
                    upcomingMovieList.addAll(res.data!!.results)
                    upcomingAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showToast(res.msg.toString())
                }
            }
        })

        viewModel.loadPopular().observe(requireActivity(), Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                    if (popularMovieList.isNullOrEmpty())
                        popularSkeleton.showSkeleton()
                }
                Status.SUCCESS -> {
                    popularSkeleton.showOriginal()
                    popularMovieList.clear()
                    popularMovieList.addAll(res.data!!.results)
                    popularAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showToast(res.msg.toString())
                }
            }
        })

        viewModel.loadTopRated().observe(requireActivity(), Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                    if (topRatedMovieList.isNullOrEmpty())
                        topRatedSkeleton.showSkeleton()
                }
                Status.SUCCESS -> {
                    topRatedSkeleton.showOriginal()
                    topRatedMovieList.clear()
                    topRatedMovieList.addAll(res.data!!.results)
                    topRatedAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showToast(res.msg.toString())
                }
            }
        })
    }
    private fun initAdapters() {
        upcomingAdapter = HomeRecyclerViewAdapter(requireContext(), upcomingMovieList)
        binding.recyclerViewUpcoming.adapter = upcomingAdapter

        popularAdapter = HomeRecyclerViewAdapter(requireContext(), popularMovieList)
        binding.recyclerViewPopular.adapter = popularAdapter

        topRatedAdapter = HomeRecyclerViewAdapter(requireContext(), topRatedMovieList)
        binding.recyclerViewTopRated.adapter = topRatedAdapter
    }
    @SuppressLint("ResourceType")
    private fun initSkeletons()
    {
        viewPagerSkeleton = binding.homeViewPager.applySkeleton(R.layout.fragment_home_view_pager)

        upcomingSkeleton = binding.recyclerViewUpcoming.applySkeleton(
            R.layout.item_movie_home,
            itemCount = 10
        )

        popularSkeleton = binding.recyclerViewPopular.applySkeleton(
            R.layout.item_movie_home,
            itemCount = 10
        )

        topRatedSkeleton = binding.recyclerViewTopRated.applySkeleton(
            R.layout.item_movie_home,
            itemCount = 10
        )
    }

}