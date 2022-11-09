package com.example.movieappmvvm.di.firebaseModule

import com.example.movieappmvvm.data.repository.firebaseRepository.UserRepository
import com.example.movieappmvvm.data.repository.firebaseRepository.UserRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    abstract fun getUserRepo(userRepositoryImpl: UserRepositoryImpl): UserRepository

    companion object {
        @Provides
        fun getUserRepoImpl(firebaseAuth: FirebaseAuth): UserRepositoryImpl = UserRepositoryImpl(firebaseAuth)

        @Provides
        fun getFirebaseAuth() = Firebase.auth
    }

}