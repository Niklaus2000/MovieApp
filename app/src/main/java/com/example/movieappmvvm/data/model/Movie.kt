package com.example.movieappmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("vote_count")
    val vote_count: Int? ,
    @SerializedName("runtime")
    val runtime: Int ?,
    @SerializedName("poster_path")
    val poster_path: String ?,
    @SerializedName("overview")
    val overview: String? ,
    @SerializedName("release_date")
    val release_date: String? ,
    @SerializedName("title")
    val title: String? ,
    @SerializedName("backdrop_path")
    val backdrop_path: String ?,
    @SerializedName("popularity")
    val popularity: Number? ,
    @SerializedName("vote_average")
    val vote_average: Number?,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>?,
    @SerializedName("genre_names")
    val genre_names: List<String> ?,
    @SerializedName("genres")
    val genres: @RawValue List<Genre>?,
    @SerializedName("video")
    val video: Boolean? ,
    @SerializedName("videos")
    val videos: @RawValue Video? ,
) : Parcelable

