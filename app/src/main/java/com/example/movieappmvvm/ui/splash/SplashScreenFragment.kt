package com.example.movieappmvvm.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.SplashScreenFragmentBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import kotlinx.coroutines.launch

class SplashScreenFragment : BaseFragment<SplashScreenViewModel,SplashScreenFragmentBinding>(SplashScreenFragmentBinding::inflate) {

    override val viewModel: SplashScreenViewModel by viewModels()


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.splashFlow.collect {
                findNavController().navigate(R.id.entryPageFragment)
            }
        }
    }

}