package com.example.movieappmvvm.data.repository.firebaseRepository

import com.example.movieappmvvm.core.response.AuthResult
import com.example.movieappmvvm.ui.loginAndRegister.userData.User

interface UserRepository {
    suspend fun createUser(user: User): AuthResult
    suspend fun loginUser(user: User): AuthResult
    suspend fun logOut(user: User)

}
