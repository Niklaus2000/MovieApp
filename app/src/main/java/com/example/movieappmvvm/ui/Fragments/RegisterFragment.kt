package com.example.movieappmvvm.ui.Fragments

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieappmvvm.Model.AuthenticationRepository
import com.example.movieappmvvm.R
import com.example.movieappmvvm.RegisterViewModelFactory
import com.example.movieappmvvm.ViewModel.RegisterViewModel
import com.example.movieappmvvm.databinding.FragmentRegisterBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.google.firebase.auth.FirebaseUser

//import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment :
    BaseFragmentBinding<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

//    private var viewModel: RegisterViewModel by viewModels()
    private lateinit var viewModel: RegisterViewModel


    override fun start() {
        val repo = AuthenticationRepository()
        val viewModelFactory = RegisterViewModelFactory(repo)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[RegisterViewModel::class.java]
        //ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[RegisterViewModel::class.java]
//        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding.btnLogRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnLogRegister.setOnClickListener {
            onAttach(requireActivity().applicationContext)
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        userRegister()

    }

    override fun observes() {
        viewModel.getUserData().observe(viewLifecycleOwner) { firebaseUser ->
            if ( firebaseUser != null) {
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext() , "dsasds" , Toast.LENGTH_SHORT).show()
            }
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


    private fun userRegister() {

        binding.button.setOnClickListener {
            val email: String = binding.emailEditText.text.toString()
            val pass: String = binding.passwordEditText.text.toString()
            val name: String = binding.nameEditText.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty() && name.isNotEmpty()) {
                viewModel.register(email , pass)
            }

        }
    }

//    private var _binding: FragmentRegisterBinding? = null
//    private val binding get() = _binding!!
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater , container: ViewGroup? ,
//        savedInstanceState: Bundle? ,
//    ): View {
//
//        _binding = FragmentRegisterBinding.inflate(inflater , container , false)
//        return binding.root


//        binding.btnLogRegister.setOnClickListener {
//
//            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
//
//        }


}


//    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
//        super.onViewCreated(view , savedInstanceState)

////        onAttach(requireActivity())
//        binding.btnLogRegister.setOnClickListener {
//            onAttach(requireActivity().applicationContext)
//            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
//        }
//  }


//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        val callback: OnBackPressedCallback =
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    activity?.overridePendingTransition(R.anim.slide_from_left ,
//                        R.anim.slide_to_right)
//
//                }
//            }
//        requireActivity().onBackPressedDispatcher.addCallback(requireActivity() , callback)
//    }
