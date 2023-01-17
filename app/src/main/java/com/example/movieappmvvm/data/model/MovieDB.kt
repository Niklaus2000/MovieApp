package com.example.movieappmvvm.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "MovieDB")
@Parcelize
data class MovieDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val poster_path: String,
    val overview: String,
    val title: String,
    val backdrop_path: String
): Parcelable


