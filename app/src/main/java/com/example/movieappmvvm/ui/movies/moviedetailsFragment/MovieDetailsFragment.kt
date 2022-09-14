package com.example.movieappmvvm.ui.movies.moviedetailsFragment

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import coil.load
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Cast
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.data.model.Status
import com.example.movieappmvvm.databinding.FragmentMovieDetailsBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter.CastRecyclerViewAdapter
import com.example.movieappmvvm.ui.movies.moviedetailsFragment.dialog.VideoPlayerDialog
import com.example.movieappmvvm.utils.CONSTANTS
import com.example.movieappmvvm.utils.showToast
import com.example.movieappmvvm.utils.toHours
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalStdlibApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MovieDetailsFragment :
    BaseFragmentBinding<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) , View.OnClickListener  {


    private lateinit var movie: Movie
    private val viewModel: MovieDetailsViewModel by viewModels()

    private var castList: ArrayList<Cast> = ArrayList()
    private lateinit var castRecyclerViewAdapter: CastRecyclerViewAdapter
    private lateinit var castSkeleton: Skeleton


    override fun start() {
        movie = requireArguments().get(CONSTANTS.movie) as Movie


        initAdapters()
        loadData()
        loadCast()



        viewModel.movieName.value = movie.title
        viewModel.movie.value = movie
        viewModel.getVideos(movie.id)



        binding.buttonBack.setOnClickListener(this)
        binding.fabPlayButton.setOnClickListener(this)

        viewModel.getMoviesDetails(movie.id)




    }


    private fun initAdapters() {
        castRecyclerViewAdapter = CastRecyclerViewAdapter(requireContext() , castList)
        binding.recyclerViewCast.adapter = castRecyclerViewAdapter
        castSkeleton = binding.recyclerViewCast.applySkeleton(R.layout.item_cast , 10)

    }

    @SuppressLint("SetText")
    private fun loadData() {

        viewModel.movie.observe(requireActivity() , Observer {

            var genre: String = ""
            if (!it.genres.isNullOrEmpty())
                for (i in 0..it.genres.size - 1) {
                    genre += CONSTANTS.getGenreMap()[it.genres[i].id].toString()
                    if (i != it.genres.size - 1) {
                        genre += "• "
                    }
                }
            binding.apply {
                textMovieName.text = it!!.title
                textRating.text = "${it.vote_average}/10"
                textReleaseDate.text = it.release_date
                textDescription.text = it.overview
                if (it.runtime != null)
                    textLength.text = toHours(it.runtime!!)
                textGenres.text = genre

                detailsBannerImage.load(CONSTANTS.ImageBaseURL + it.backdrop_path) {
                    placeholder(CONSTANTS.viewPagerPlaceHolder.random())
                    error(CONSTANTS.viewPagerPlaceHolder.random())
                }
                imagePoster.load(CONSTANTS.ImageBaseURL + it.poster_path) {
                    placeholder(CONSTANTS.moviePlaceHolder.random())
                    error(CONSTANTS.moviePlaceHolder.random())
                }

            }
        })

    }
    private fun loadCast() {
        viewModel.loadCast(movie.id).observe(requireActivity(), Observer {
            when (it.status) {
                Status.LOADING -> {
                    if (castList.isNullOrEmpty())
                        castSkeleton.showSkeleton()
                }
                Status.SUCCESS -> {
                    castSkeleton.showOriginal()
                    castList.clear()
                    castList.addAll(it.data!!.cast)
                    castRecyclerViewAdapter.notifyDataSetChanged()

                    if (castList.isNullOrEmpty()) {
                        binding.headingCast.visibility = View.GONE
                    } else {
                        binding.headingCast.visibility = View.VISIBLE
                    }

                }
                Status.ERROR -> {
                    showToast("Something went wrong!")
                }
            }
        })
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.fabPlayButton -> {
                if (viewModel.videos.value != null && viewModel.videos.value!!.results.size != 0) {
                    val videoDialog = VideoPlayerDialog(viewModel.videos.value!!.results[0].key)
                    videoDialog.show(childFragmentManager , "Video Dialog")
                } else {
                    showToast("Video not found!")
                }
            }
            R.id.button_back -> {
                binding.root.findNavController().navigateUp()
            }

        }

    }
}

