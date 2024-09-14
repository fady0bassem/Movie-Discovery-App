package com.fadybassem.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductionCountriesResponseModel(
    @SerializedName("iso_3166_1") var iso31661: String? = null,
    @SerializedName("name") var name: String? = null,
)