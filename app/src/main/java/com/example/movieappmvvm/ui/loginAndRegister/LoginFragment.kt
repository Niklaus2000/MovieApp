package com.example.movieappmvvm.ui.loginAndRegister

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentLoginBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.loginAndRegister.userData.User
import com.example.movieappmvvm.ui.loginAndRegister.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<LoginViewModel , FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        setUpNav()
        loginUser()
    }

    private fun setUpNav(): Unit = with(binding) {
        btnRegLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            activity?.overridePendingTransition(R.anim.slide_from_right , R.anim.slide_from_left)
        }
    }


    private fun loginUser(): Unit = with(binding) {
        buttonLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            val user = User(email = email, password = password)
            viewModel.login(user)

            collectFlow(viewModel.channelFlow) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

    }
}