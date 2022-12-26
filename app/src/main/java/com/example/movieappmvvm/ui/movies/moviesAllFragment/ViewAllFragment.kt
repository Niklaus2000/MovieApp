package com.example.movieappmvvm.ui.movies.moviesAllFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieappmvvm.core.response.UiState
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentViewAllBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter.ViewAllAdapter
import com.example.movieappmvvm.utils.CONSTANTS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ViewAllFragment :
    BaseFragment<ViewAllViewModel , FragmentViewAllBinding>(FragmentViewAllBinding::inflate) ,
    OnItemClick<MoviesUIModel> {
    override val viewModel: ViewAllViewModel by viewModels()

    private var pageType: String? = null
    private lateinit var movieAdapter: ViewAllAdapter

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        setUpAdapter()
        viewModel.getPopular()
        initAdapters()




    }


    private fun setUpAdapter(): Unit = with(binding) {

        val safeArgs = ViewAllFragmentArgs.fromBundle(requireArguments())
        pageType = safeArgs.name
        pageTitle.text = pageType

        when (pageType) {
            CONSTANTS.Upcoming -> Toast.makeText(requireContext() , "UpComing" , Toast.LENGTH_SHORT).show()
            CONSTANTS.Popular -> fetchPopular()
            CONSTANTS.TopRated -> Toast.makeText(requireContext() , "TopRated" , Toast.LENGTH_SHORT).show()
        }

        binding.buttonBack.setOnClickListener {
            binding.root.findNavController().navigateUp()
        }

    }
//

    private fun fetchPopular() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel._popularStateFlow.collect{
                when (it) {
                    is UiState.Error -> Toast.makeText(
                        requireContext(),
                        "Error",
                        Toast.LENGTH_LONG
                    ).show()
                    is UiState.Loading -> Toast.makeText(
                        requireContext(),
                        "Loading",
                        Toast.LENGTH_LONG
                    ).show()
                    is UiState.Success -> movieAdapter.submitData(it.data.map { it1 ->
                        MoviesUIModel(
                            it1.id,
                            it1.poster_path,
                            it1.title,
                            it1.vote_average!!.toFloat(),
                            it1.release_date ,
                            it1.runtime,
                            it1.genres,
                            it1.overview,
                            it1.backdrop_path,
                        )
                    })
                }
            }
        }
    }

    private fun initAdapters(): Unit = with(binding) {
        movieAdapter = ViewAllAdapter(this@ViewAllFragment)
        movieRecyclerView.adapter = movieAdapter
        movieRecyclerView.layoutManager =  StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)


    }


    override fun onItemClick(item: MoviesUIModel) {
        findNavController().navigate(ViewAllFragmentDirections.actionViewAllFragmentToMovieDetailsFragment(item.id))
    }

}
