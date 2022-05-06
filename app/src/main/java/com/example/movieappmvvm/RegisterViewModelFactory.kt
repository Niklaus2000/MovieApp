package com.example.movieappmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmvvm.Model.AuthenticationRepository
import com.example.movieappmvvm.ViewModel.RegisterViewModel

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(private val authRepo: AuthenticationRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(authRepo) as T
        }
        throw IllegalArgumentException("ViewModel Class Not Found")
    }
}