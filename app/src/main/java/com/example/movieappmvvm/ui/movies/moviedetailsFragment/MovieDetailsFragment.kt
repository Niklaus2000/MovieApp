package com.example.movieappmvvm.ui.movies.moviedetailsFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.loadAny
import com.example.movieappmvvm.R
import com.example.movieappmvvm.core.response.DetailsUIState
import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentMovieDetailsBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter.CastRecyclerViewAdapter
import com.example.movieappmvvm.utils.CONSTANTS
import com.example.movieappmvvm.utils.toHours
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalStdlibApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MovieDetailsFragment :
    BaseFragment<MovieDetailsViewModel , FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {

    override val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var castRecyclerViewAdapter: CastRecyclerViewAdapter


    override fun onViewCreated(view: View , savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view , savedInstanceState)

        buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }


        checkBookmark()

        buttonBookmark.setOnClickListener {
            viewModel.bookmarkMovie()
            val safeArgsDetails = MovieDetailsFragmentArgs.fromBundle(requireArguments())
            viewModel.checkBookmarkExist(movie_id = safeArgsDetails.movieId)

        }



        initAdapters()
        val safeArgs = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        viewModel.getCast(movie_id = safeArgs.movieId)


        val safeArgsDetails = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        viewModel.getMoviesDetails(movie_id = safeArgsDetails.movieId)



        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfCast.collectLatest {
                it.apply(binding , castRecyclerViewAdapter)
            }


        }



        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moviesOfDetailsState.collectLatest {
                when (it) {
                    is DetailsUIState.Loading -> Toast.makeText(requireContext() ,
                        "Loading" ,
                        Toast.LENGTH_SHORT).show()
                    is DetailsUIState.Success -> binding.apply {
                        var genre: String = ""
                        if (!it.data.genres.isNullOrEmpty())
                            for (i in 0..it.data.genres.size - 1) {
                                genre += CONSTANTS.getGenreMap()[it.data.genres[i].id].toString()
                                if (i != it.data.genres.size - 1) {
                                    genre += "• "
                                }
                            }

                        it.data.id
                        imagePoster.load(CONSTANTS.ImageBaseURL + it.data.image)
                        movieNameTextView.text = it.data.title
                        ratingTextView.text = " ${it.data.vote_average}/10"
                        if (it.data.runtime != null)
                            textLength.text = toHours(it.data.runtime)
                        dateTextView.text = it.data.release_date
                        descriptionTextView.text = it.data.overview
                        detailsBannerImage.load(CONSTANTS.ImageBaseURL + it.data.bannerImage)
                        textGenres.text = genre


                    }
                    is DetailsUIState.Error -> Toast.makeText(requireContext() ,
                        "Error" ,
                        Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    private fun checkBookmark() {
        viewModel.movieBookMark.observe(viewLifecycleOwner , Observer {
            binding.apply {
                if (it) {
                    buttonBookmark.setImageResource(R.drawable.ic_bookmark_done)
                } else {
                    buttonBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        })



    }


    private fun initAdapters(): Unit = with(binding) {
        castRecyclerViewAdapter = CastRecyclerViewAdapter()
        castRecyclerView.adapter = castRecyclerViewAdapter
        castRecyclerView.layoutManager =
            LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
    }

}


