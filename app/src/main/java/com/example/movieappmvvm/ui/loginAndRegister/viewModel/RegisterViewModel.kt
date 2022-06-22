package com.example.movieappmvvm.ui.loginAndRegister.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    private lateinit var user: FirebaseAuth

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> get() = _registerStatus


     fun registerUser(email: String? , password: String? , repeatPassword: String? , name: String?  ) {
        user = FirebaseAuth.getInstance()
        if (email!!.isNotEmpty() && password!!.isNotEmpty() && repeatPassword!!.isNotEmpty() && name!!.isNotEmpty()) {
            if (password == repeatPassword) {
                user.createUserWithEmailAndPassword(email , password)
                    .addOnCompleteListener { task ->
                        _registerStatus.value = task.isSuccessful
                    }
            }
        }
    }
}

