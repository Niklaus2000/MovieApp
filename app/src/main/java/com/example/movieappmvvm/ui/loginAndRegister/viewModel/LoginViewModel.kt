package com.example.movieappmvvm.ui.loginAndRegister.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.core.AuthResult
import com.example.movieappmvvm.data.repository.firebaseRepository.UserRepository
import com.example.movieappmvvm.ui.loginAndRegister.userData.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val channel = Channel<AuthResult>()
    val channelFlow = channel.receiveAsFlow()


    fun login(user: User) {
        viewModelScope.launch {
            channel.send(userRepository.loginUser(user))
        }
    }


}

