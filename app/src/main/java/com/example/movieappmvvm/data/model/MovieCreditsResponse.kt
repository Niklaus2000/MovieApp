package com.example.movieappmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCreditsResponse(
    @SerializedName("cast")
    val cast : ArrayList<Movie>?
):Parcelable