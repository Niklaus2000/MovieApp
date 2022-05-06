package com.example.movieappmvvm.Model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class AuthenticationRepository {
    private val application: Application? = null
    private var firebaseUserMutableLiveData: MutableLiveData<FirebaseUser> =
        getFirebaseUserMutableLiveData()
    private var userLoggedMutableLiveData: MutableLiveData<Boolean?> =
        getUserLoggedMutableLiveData()
    private val firebaseStoreUserMutableLiveData: MutableLiveData<FirebaseFirestore?> =
        getUserIdLoggedMutableLiveData()

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val fStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var userID: String? = null

   // private val personCollectionRef = Firebase.firestore.collection("persons")


   // private val personCollectionRef = Firebase.firestore.collection("persons")


    //        fun register(username: String?, email: String? ) {
//        auth.createUserWithEmailAndPassword( username!!, email!!).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                firebaseUserMutableLiveData.postValue(auth.currentUser)
//            } else {
//                Toast.makeText(application , task.exception!!.message , Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    fun register(email: String ,  password: String) {
        auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener { task ->
           //
        // if (task.isSuccessful) {
//                val documentReference = fStore.collection("user").document(userID!!)

               // val ravi = firebaseStoreUserMutableLiveData.postValue(fStore.collection("user").document(userID!!).toString())
               //  val documentReference = firebaseStoreUserMutableLiveData.postValue(fStore.collection("user_profile").document(userID!!))


//                user["Password"] = password


//                documentReference.set(user).addOnSuccessListener {
//                    firebaseUserMutableLiveData.postValue(auth.currentUser)
//                }.addOnFailureListener { }

            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            } else {
                 Toast.makeText(application , task.exception!!.message , Toast.LENGTH_SHORT).show()
//                firebaseUserMutableLiveData.postValue(false)
            }

          //  }
        }
    }


//    private fun savePerson(user: User) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            personCollectionRef.add(user).await()
//            firebaseStoreUserMutableLiveData.postValue(fStore.collection("user").document())
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@MainActivity, "Successfully saved data.", Toast.LENGTH_LONG).show()
//            }
//        } catch(e: Exception) {
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
//            }
//        }
//    }

//    private fun savePerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            personCollectionRef.add(person).await()
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@MainActivity, "Successfully saved data.", Toast.LENGTH_LONG).show()
//            }
//        } catch(e: Exception) {
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
//            }
//        }
//    }


    fun login(email: String? , pass: String?) {
        auth.signInWithEmailAndPassword(email!! , pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            } else {
                Toast.makeText(application , task.exception!!.message , Toast.LENGTH_SHORT).show()
             //   Toast.makeText("dsadaf" , task.exception!!.message , Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }

    fun getFirebaseUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return firebaseUserMutableLiveData
    }

    private fun getUserLoggedMutableLiveData(): MutableLiveData<Boolean?> {
        return userLoggedMutableLiveData
    }

    private fun getUserIdLoggedMutableLiveData(): MutableLiveData<FirebaseFirestore?> {
        return firebaseStoreUserMutableLiveData
    }
}













