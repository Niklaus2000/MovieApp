package com.example.movieappmvvm.ui.loginAndRegister.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmvvm.ui.loginAndRegister.userData.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RegisterViewModel : ViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> get() = _registerStatus


     fun registerUser(email: String? , password: String? , repeatPassword: String? , name: String) {
         firebaseAuth = FirebaseAuth.getInstance()
        if (email!!.isNotEmpty() && password!!.isNotEmpty() && repeatPassword!!.isNotEmpty() && name.isNotEmpty()) {
            if (password == repeatPassword) {
                firebaseAuth.createUserWithEmailAndPassword(email , password)
                    .addOnCompleteListener { task ->
                       // _registerStatus.value = task.isSuccessful

                        val user = firebaseAuth.currentUser
                        val uid = user?.uid

                        val activeUser = User(name)
                        database.child(uid!!).setValue(activeUser).addOnCompleteListener {
                            if (it.isSuccessful) {
                                _registerStatus.value = task.isSuccessful
                            }
                        }
                    }
            }
        }
    }
}

