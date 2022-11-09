package com.example.movieappmvvm.ui.loginAndRegister

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentRegisterBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.example.movieappmvvm.ui.loginAndRegister.userData.User
import com.example.movieappmvvm.ui.loginAndRegister.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel,FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override val viewModel: RegisterViewModel by viewModels()


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)


        binding.btnLogRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnLogRegister.setOnClickListener {
            onAttach(requireActivity().applicationContext)
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        initView()

    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.overridePendingTransition(R.anim.slide_from_left ,
                        R.anim.slide_to_right)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity() , callback)
    }

    private fun initView(): Unit = with(binding) {
        signUpButton.setOnClickListener {
            val email = emailEditTextView.text.toString().trim()
            val password = passwordEditTextView.text.toString().trim()
            val repeatPassword = repeatPasswordEditTextView.text.toString().trim()
            val user = User(email = email , password = password , repeatPassword = repeatPassword)
            viewModel.register(user)
        }
        collectFlow(viewModel.channelFlow) {
            findNavController().navigate(R.id.homeFragment)
        }
    }

}


