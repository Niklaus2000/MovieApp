package com.example.movieappmvvm.ui.movies.bookMark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.core.response.BookMarkState
import com.example.movieappmvvm.core.response.HandleResponse
import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.FragmentBookMarkBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.bookMark.adapter.BookMarkAdapter
import com.example.movieappmvvm.ui.movies.moviesAllFragment.adapter.ViewAllAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<BookMarkViewModel, FragmentBookMarkBinding>(FragmentBookMarkBinding::inflate),OnItemClick<MovieDB> {

    override val viewModel: BookMarkViewModel by viewModels()
    private lateinit var bookMarkAdapter: BookMarkAdapter

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        viewModel.fetchBookMark()
        initAdapters()
        collectInfo()
        searchMovieItem()
    }

    private fun collectInfo() {
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.bookMarkState.collectLatest {
                when(it) {
                    is BookMarkState.Error -> Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                    is BookMarkState.Success -> bookMarkAdapter.submitList(it.data)
                }
            }
        }
    }
    private fun initAdapters() = with(binding) {
        bookMarkAdapter = BookMarkAdapter(this@BookMarkFragment)
        searchRecyclerView.adapter = bookMarkAdapter
        searchRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

    }
    private fun searchMovieItem() = with(binding) {
        searchEditText.doAfterTextChanged {
            viewModel.searchMovie(it.toString())
        }
    }

    override fun onItemClick(item: MovieDB ) {
        TODO("Not yet implemented")
    }


}