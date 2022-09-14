package com.example.movieappmvvm.ui.loginAndRegister.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel()  {

    private lateinit var user: FirebaseAuth

    private val _loggedStatus = MutableLiveData<LogStatus>()
    val loggedStatus: LiveData<LogStatus> get() = _loggedStatus


    fun signIn(email: String? , password: String?) {
        user = FirebaseAuth.getInstance()
        if (email!!.isNotEmpty() && password!!.isNotEmpty() ) {

            user.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) _loggedStatus.value = LogStatus.Success()
                    else _loggedStatus.value = LogStatus.Error()

                }
        }
    }

}


