package com.example.movieappmvvm.ui.loginAndRegister.viewModel

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.movieappmvvm.R

interface LogStatus  {
    fun apply(navController: NavController , context: Context)
    class Success: LogStatus {
        override fun apply(navController: NavController , context: Context) {
            navController.navigate(R.id.homeFragment)
        }
    }
    class Error: LogStatus {
        override fun apply(navController: NavController , context: Context) {
            Toast.makeText(context,"Failure" , Toast.LENGTH_SHORT).show()
        }
    }
}