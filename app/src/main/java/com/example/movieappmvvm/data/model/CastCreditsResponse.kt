package com.example.movieappmvvm.data.model

import com.google.gson.annotations.SerializedName

data class CastCreditsResponse (
    @SerializedName("id")
    val id : Int,
    @SerializedName("cast")
    val cast : ArrayList<Cast>?

)