package com.example.movieappmvvm.core.response

import android.view.View
import com.example.movieappmvvm.extension.showSnack

interface AuthResult {

    fun applyResult(view: View)

    class SuccessResult: AuthResult {
        override fun applyResult(view: View) {
            view.showSnack("True")
        }
    }


    class ErrorResult(private val message: String) : AuthResult {
        override fun applyResult(view: View) {
            view.showSnack(message)
        }
    }

}