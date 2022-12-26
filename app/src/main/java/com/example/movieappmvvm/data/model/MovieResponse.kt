package com.example.movieappmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieResponse  (
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Movie>,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int?
): Parcelable