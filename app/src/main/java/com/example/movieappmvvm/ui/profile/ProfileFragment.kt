package com.example.movieappmvvm.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieappmvvm.databinding.FragmentProfileBinding
import com.example.movieappmvvm.databinding.FragmentRegisterBinding
import com.example.movieappmvvm.ui.base.BaseFragmentBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment :  BaseFragmentBinding<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private lateinit var user: FirebaseAuth

    override fun start() {
       // saveNameFirebase()
     //   saveNameFirebase()

    }
//    private fun saveNameFirebase() {
//        user = FirebaseAuth.getInstance()
//        if(user.currentUser != null) {
//            user.currentUser?.let {
//                binding.nameEditTextFirebase.text = it.
//            }
//        }
//    }
}