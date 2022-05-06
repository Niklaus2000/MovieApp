package com.example.movieappmvvm.ui.login

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentLoginBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding


class LoginFragment : BaseFragmentBinding<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private var viewModel: LoginViewModel? = null
    override fun start() {
        binding.btnRegLogin.setOnClickListener {


            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_from_left)

        }
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }
    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this ,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[LoginViewModel::class.java]
        viewModel!!.getUserData()?.observe(this) { firebaseUser ->
            if (firebaseUser != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

    }










}