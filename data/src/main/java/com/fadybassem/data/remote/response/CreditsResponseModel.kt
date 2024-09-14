package com.fadybassem.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreditsResponseModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("cast" ) var cast : ArrayList<CastResponseModel> = arrayListOf(),
    @SerializedName("crew" ) var crew : ArrayList<CrewResponseModel> = arrayListOf()
)
