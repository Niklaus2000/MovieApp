package com.example.movieappmvvm.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentHomeBinding
import com.example.movieappmvvm.databinding.FragmentProfileBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val fragment = MoviesFragment()
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
                R.id.tv -> {
                    val tvFragment = TvFragment()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, tvFragment).commit()
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