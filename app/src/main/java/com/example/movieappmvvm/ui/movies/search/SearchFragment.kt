package com.example.movieappmvvm.ui.movies.search


import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.movieappmvvm.databinding.FragmentSearchBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.ui.movies.search.adapter.SearchRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber.e


@ExperimentalStdlibApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragmentBinding<FragmentSearchBinding>(FragmentSearchBinding::inflate) {


    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchRecyclerViewAdapter

    override fun start() {
        searchMovieAndAdapterInit()

    }
    private fun searchMovieAndAdapterInit() {
        searchAdapter = SearchRecyclerViewAdapter()
        binding.searchRecyclerView.adapter = searchAdapter

        binding.buttonBack.setOnClickListener {
            it.findNavController().navigateUp()
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                getSearchResult()
            }

        })

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getSearchResult()
                true
            }
            false
        }
    }
    private fun getSearchResult() {
        if (!binding.searchEditText.text.isNullOrEmpty())
            viewModel.getSearchMovie(binding.searchEditText.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    searchAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                    e(it.toString())
                })
    }
}