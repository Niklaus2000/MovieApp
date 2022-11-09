package com.example.movieappmvvm.ui.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.Status
import com.example.movieappmvvm.databinding.FragmentMoviesBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.movies.adapter.HomeRecyclerViewAdapter
import com.example.movieappmvvm.ui.movies.adapter.HomeViewPagerAdapter
import com.example.movieappmvvm.utils.CONSTANTS
import com.example.movieappmvvm.utils.showToast
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel,FragmentMoviesBinding>(FragmentMoviesBinding::inflate) ,View.OnClickListener{

    override val viewModel: MoviesViewModel by viewModels()
    private lateinit var navController: NavController

//    private lateinit var moviesAdapter: MoviesAdapter

    private var upcomingMovieList: ArrayList<Movie> = ArrayList()
    private var popularMovieList: ArrayList<Movie> = ArrayList()
    private var topRatedMovieList: ArrayList<Movie> = ArrayList()

    private lateinit var upcomingAdapter: HomeRecyclerViewAdapter
    private lateinit var popularAdapter: HomeRecyclerViewAdapter
    private lateinit var topRatedAdapter: HomeRecyclerViewAdapter

    private lateinit var viewPagerSkeleton: Skeleton
    lateinit var upcomingSkeleton: Skeleton
    lateinit var topRatedSkeleton: Skeleton
    lateinit var popularSkeleton: Skeleton


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        fetchData()
        initAdapters()
        initSkeletons()

        binding.textViewAllPopular.setOnClickListener(this)
        binding.textViewAllTopRated.setOnClickListener(this)
        binding.textViewAllUpcoming.setOnClickListener(this)
        binding.homeSearchButton.setOnClickListener(this)

        navController = Navigation.findNavController(binding.root)
    }

    private fun fetchData() {
        viewModel.loadNowPlaying().observe(requireActivity() , Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                    viewPagerSkeleton.showSkeleton()
                }
                Status.SUCCESS -> {
                    viewPagerSkeleton.showOriginal()
                    binding.homeViewPager.adapter =
                        HomeViewPagerAdapter(childFragmentManager , lifecycle , res.data!!.results)
                }
                Status.ERROR -> {
                    showToast(res.msg.toString())
                }
            }
        })

        viewModel.loadUpcoming().observe(requireActivity() , Observer { res ->
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

        viewModel.loadPopular().observe(requireActivity() , Observer { res ->
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

        viewModel.loadTopRated().observe(requireActivity() , Observer { res ->
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

    private fun initAdapters(): Unit = with(binding) {
        upcomingAdapter = HomeRecyclerViewAdapter(requireContext() , upcomingMovieList)
        recyclerViewUpcoming.adapter = upcomingAdapter

        popularAdapter = HomeRecyclerViewAdapter(requireContext() , popularMovieList)
        recyclerViewPopular.adapter = popularAdapter

        topRatedAdapter = HomeRecyclerViewAdapter(requireContext() , topRatedMovieList)
        recyclerViewTopRated.adapter = topRatedAdapter

//        moviesAdapter = MoviesAdapter()
//        binding.rvMovies.adapter = moviesAdapter
//        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    @SuppressLint("ResourceType")
    private fun initSkeletons() {
        viewPagerSkeleton = binding.homeViewPager.applySkeleton(R.layout.fragment_home_view_pager)

        upcomingSkeleton = binding.recyclerViewUpcoming.applySkeleton(
            R.layout.item_movie_home ,
            itemCount = 10
        )

        popularSkeleton = binding.recyclerViewPopular.applySkeleton(
            R.layout.item_movie_home ,
            itemCount = 10
        )

        topRatedSkeleton = binding.recyclerViewTopRated.applySkeleton(
            R.layout.item_movie_home ,
            itemCount = 10
        )
    }
    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.home_search_button -> {
                navController.navigate(R.id.searchFragment)
            }
            R.id.text_view_all_popular -> {
                val bundle = bundleOf(CONSTANTS.viewAll to CONSTANTS.Popular)
                navController.navigate(R.id.viewAllFragment, bundle)
            }
            R.id.text_view_all_top_rated -> {
                val bundle = bundleOf(CONSTANTS.viewAll to CONSTANTS.TopRated)
                navController.navigate(R.id.viewAllFragment, bundle)
            }
            R.id.text_view_all_upcoming -> {
                val bundle = bundleOf(CONSTANTS.viewAll to CONSTANTS.Upcoming)
                navController.navigate(R.id.viewAllFragment, bundle)
            }


        }

    }



}
