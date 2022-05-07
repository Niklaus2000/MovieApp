package com.example.movieappmvvm.ui.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.SplashScreenFragmentBinding
import com.example.movieappmvvm.ui.splash.SplashScreenViewModel
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashScreenFragment :BaseFragmentBinding<SplashScreenFragmentBinding>(SplashScreenFragmentBinding::inflate) {

    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun start() {
        viewLifecycleOwner.lifecycleScope.launch {
            splashScreenViewModel.splashFlow.collect {
                findNavController().navigate(R.id.action_splashScreenFragment_to_entryPageFragment)
            }
        }
    }

}