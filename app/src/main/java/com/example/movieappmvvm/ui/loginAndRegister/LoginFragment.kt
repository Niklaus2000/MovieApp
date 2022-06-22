package com.example.movieappmvvm.ui.loginAndRegister

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentLoginBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.ui.loginAndRegister.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragmentBinding<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun start() {
        binding.btnRegLogin.setOnClickListener {


            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            activity?.overridePendingTransition(R.anim.slide_from_right , R.anim.slide_from_left)

        }
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.buttonLogin.setOnClickListener {
            loginUser()
        }
    }
    private fun loginUser() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()


        viewModel.signIn(email , password)
        viewModel.loggedStatus.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext() , "Failure" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}