package com.example.movieappmvvm.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentHomeBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.movies.MoviesFragment
import com.example.movieappmvvm.ui.profile.ProfileFragment
import com.example.movieappmvvm.ui.tv.TvFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        setUpBottomMenu()
    }

    private fun setUpBottomMenu() {

        binding.bottomMenu.setOnItemSelectedListener {
            when(it) {
                R.id.movies -> {
                    openMainFragment(MoviesFragment())
                    true
                }
                R.id.tv -> {
                    openMainFragment(TvFragment())
                    true
                }
                R.id.profile -> {
                    openMainFragment(ProfileFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

    }


    private fun openMainFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}


