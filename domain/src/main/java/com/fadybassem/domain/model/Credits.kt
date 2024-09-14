package com.fadybassem.domain.model

data class Credits(
    var id: Int? = null,
    var cast: ArrayList<Cast> = arrayListOf(),
    var crew: ArrayList<Crew> = arrayListOf(),
)
