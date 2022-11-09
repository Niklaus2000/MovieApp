package com.example.movieappmvvm.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(message: String , length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(this , message , length)
    snack.show()
}