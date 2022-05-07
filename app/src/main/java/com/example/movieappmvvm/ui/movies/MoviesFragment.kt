package com.example.movieappmvvm.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieappmvvm.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater , container , false)
        return binding.root
    }


}