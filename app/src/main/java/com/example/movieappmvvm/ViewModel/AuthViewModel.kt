package com.example.movieappmvvm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieappmvvm.Model.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser


//class AuthViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository: AuthenticationRepository? = null
//    private val userData: MutableLiveData<FirebaseUser>? = null
//    private val loggedStatus: MutableLiveData<Boolean>? = null
//
//    fun getUserData(): MutableLiveData<FirebaseUser>? {
//        return userData
//    }
//    fun getLoggedStatus(): MutableLiveData<Boolean>? {
//        return loggedStatus
//    }
//
//
//    fun register(email: String , pass: String, username: String) {
//        repository?.register(email , pass, username )
//    }
//
//    fun signIn(email: String? , pass: String?) {
//        repository?.login(email , pass)
//    }
//
//
//
//}
