package com.example.movieappmvvm.data.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Actor (
    @SerializedName("id")
    val id: Int,
    @SerializedName("popularity")
    val popularity: Number,
    @SerializedName("name")
    val name: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("also_known_as")
    val also_known_as: ArrayList<String>,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("place_of_birth")
    val place_of_birth: String,
    @SerializedName("profile_path")
    val profile_path: String,
    @SerializedName("known_for_department")
    val known_for_department: String,
    @SerializedName("movie_credits")
    val movie_credits: JSONObject
)