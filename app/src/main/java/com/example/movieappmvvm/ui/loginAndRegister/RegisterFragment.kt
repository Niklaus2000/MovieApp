package com.example.movieappmvvm.ui.loginAndRegister

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.FragmentRegisterBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.example.movieappmvvm.ui.loginAndRegister.viewModel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragmentBinding<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun start() {

        binding.btnLogRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnLogRegister.setOnClickListener {
            onAttach(requireActivity().applicationContext)
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.button.setOnClickListener {
            registerUsers()
        }


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

    private fun registerUsers() {
        with(binding) {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val repeatPassword = repeatPasswordEditText.text.toString()
            val name = nameEditText.text.toString()

            viewModel.registerUser(email , password , repeatPassword , name)
            viewModel.registerStatus.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext() , "Failure" , Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}


