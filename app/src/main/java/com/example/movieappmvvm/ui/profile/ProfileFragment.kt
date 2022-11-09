package com.example.movieappmvvm.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappmvvm.databinding.FragmentProfileBinding
import com.example.movieappmvvm.ui.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment :  BaseFragment<ProfileViewModel,FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
    }
}