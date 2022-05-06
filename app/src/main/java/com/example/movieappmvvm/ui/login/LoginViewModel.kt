package com.example.movieappmvvm.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

//class LoginViewModel: ViewModel() {
//}

class LoginViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository: AuthenticationRepository? = null
    private val userData: MutableLiveData<FirebaseUser>? = null
    private val loggedStatus: MutableLiveData<Boolean>? = null

    fun getUserData(): MutableLiveData<FirebaseUser>? {
        return userData
    }

    fun signIn(email: String? , pass: String?) {
    //    repository?.login(email , pass)
    }



}