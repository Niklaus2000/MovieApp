package com.example.movieappmvvm.ui.loginAndRegister.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel: ViewModel()  {

    private lateinit var user: FirebaseAuth
    private val _loggedStatus = MutableLiveData<Boolean>()
    val loggedStatus: LiveData<Boolean> get() = _loggedStatus


    fun signIn(email: String? , password: String?) {
        user = FirebaseAuth.getInstance()
        if (email!!.isNotEmpty() && password!!.isNotEmpty() ) {

            user.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    _loggedStatus.value = task.isSuccessful

                }
        }
    }

}