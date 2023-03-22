package com.bedirhandroid.pokemontechcase.response.pokemon.detailmodels

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default")
    val frontImage: String? = null,
    @SerializedName("back_default")
    val backImage: String? = null
)
