package com.example.movieappmvvm.data.repository.firebaseRepository

import com.example.movieappmvvm.core.AuthResult
import com.example.movieappmvvm.ui.loginAndRegister.userData.User
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : UserRepository {

    override suspend fun createUser(user: User): AuthResult = try {
        if (user.isCorrectInputRegister()) {
            if (user.isPasswordContains()
            ) {
                firebaseAuth.createUserWithEmailAndPassword(user.email!! , user.password!!).await()
            }
        }
        AuthResult.SuccessResult()

    } catch (e: Exception) {
        AuthResult.ErrorResult(e.message!!)
    }



    override suspend fun loginUser(user: User): AuthResult = try {
        if (user.isCorrectInputLogin()) {
            firebaseAuth.signInWithEmailAndPassword(user.email!! , user.password!!).await()
            AuthResult.SuccessResult()
        } else {
            AuthResult.ErrorResult("Input is not valid.")
        }
    } catch (e: Exception) {
        AuthResult.ErrorResult(e.message!!)
    }

    override suspend fun logOut(user: User) = Unit
}


