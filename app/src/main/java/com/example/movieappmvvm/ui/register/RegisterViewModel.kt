package com.example.movieappmvvm.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmvvm.Model.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

//class RegisterViewModel {
//}
// class RegisterViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository: AuthenticationRepository
//    private var userData: MutableLiveData<FirebaseUser>? = null
//   // private val loggedStatus: MutableLiveData<Boolean>? = null
//
//    fun getUserData(): MutableLiveData<FirebaseUser>? {
//        return userData
//    }
////    fun getLoggedStatus(): MutableLiveData<Boolean>? {
////        return loggedStatus
////    }
//
//
//    fun register(email: String , pass: String, username: String) {
//        repository.register(email , pass, username )
//    }
//
//
//}
class RegisterViewModel(private val authRepo: AuthenticationRepository) : ViewModel() {

    private var userData = MutableLiveData<FirebaseUser>()

    fun getUserData(): MutableLiveData<FirebaseUser> {
        return userData
    }

    fun register(email: String , pass: String ) {
        authRepo.register(email , pass )
    }
//    fun getUserData(): MutableLiveData<FirebaseUser?>? {
//        return userData
//    }

    init {
        userData = authRepo.getFirebaseUserMutableLiveData()
    }


}
