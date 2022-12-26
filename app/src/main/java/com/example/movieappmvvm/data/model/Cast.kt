package com.example.movieappmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String,
    val original_name: String,
    val adult: Boolean,
    val character: String,
    val also_known_as: ArrayList<String>,
    @SerializedName("profile_path")
    val profile_path: String?,
    val gender: Int,
    val known_for_department: String,
    val popularity: Number,
    val credit_id: String,
    val department: String,
    val release_date: String,
    val job: String,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Number,
    val title: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String
): Parcelable