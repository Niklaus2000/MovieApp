package com.example.movieappmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentHomeBinding
import com.example.movieappmvvm.ui.movies.MoviesFragment
import com.example.movieappmvvm.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val fragment = MoviesFragment()
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root




    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        setUpBottomMenu()
    }
    private fun setUpBottomMenu() {

        binding.bottomMenu.setItemSelected(R.id.movies)
        binding.bottomMenu.setOnItemSelectedListener{
            when (it){
                R.id.movies -> {
                    openMainFragment()
                }
                R.id.profile -> {
                    val profileFragment = ProfileFragment()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, profileFragment).commit()
                }
            }
        }

    }
    private fun openMainFragment() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}