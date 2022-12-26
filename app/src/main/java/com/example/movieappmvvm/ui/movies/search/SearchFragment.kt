package com.example.movieappmvvm.ui.movies.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentSearchBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchViewModel , FragmentSearchBinding>(FragmentSearchBinding::inflate) ,
    OnItemClick<MoviesUIModel> {

    override val viewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View , savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view , savedInstanceState)


        initAdapters()

        buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }


        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence? ,
                start: Int ,
                count: Int ,
                after: Int ,
            ) {

            }

            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {

            }

            override  fun afterTextChanged(s: Editable?) {
                    runBlocking{ getSearchResult()}
            }

        })

        searchEditText.setOnEditorActionListener { _ , actionId , _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                runBlocking{ getSearchResult()}
                true
            }
            false
        }


    }

    private fun initAdapters(): Unit = with(binding) {
        searchAdapter = SearchAdapter(this@SearchFragment)
        searchRecyclerView.adapter = searchAdapter
        searchRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3 , StaggeredGridLayoutManager.VERTICAL)

    }

    private  fun getSearchResult(): Unit = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            if (!binding.searchEditText.text.isNullOrEmpty())
                viewModel.getSearch(binding.searchEditText.text.toString()).collect { it ->
                    searchAdapter.submitData(viewLifecycleOwner.lifecycle , it.map {
                        MoviesUIModel(
                            it.id ,
                            it.poster_path ,
                            it.title ,
                            it.vote_average!!.toFloat() ,
                            it.release_date ,
                            it.runtime,
                            it.genres,
                            it.overview ,
                            it.backdrop_path ,
                        )
                    })
                }
        }


//    private fun getSearchResult(): Unit = with(binding) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel._searchStateFlow.collect {
//                when (it) {
//                    is UiState.Error -> Toast.makeText(requireContext() ,
//                        "Error" ,
//                        Toast.LENGTH_LONG).show()
//                    is UiState.Loading -> Toast.makeText(requireContext() ,
//                        "Loading" ,
//                        Toast.LENGTH_LONG).show()
//                    is UiState.Success ->
//                        if (!binding.searchEditText.text.isNullOrEmpty())
//                            viewModel.getSearch(binding.searchEditText.text.toString())
//
////                        searchAdapter.submitData(it.data.map { it1 ->
////                        MoviesUIModel(
////                            it1.id ,
////                            it1.poster_path ,
////                            it1.title ,
////                            it1.vote_average!!.toString() ,
////                            it1.release_date ,
////                            it1.overview ,
////                            it1.backdrop_path ,
////                        )
////
////                    })
//
//                }
//            }
//        }
    }

    override fun onItemClick(item: MoviesUIModel) {
       findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(item.id))
    }
}

