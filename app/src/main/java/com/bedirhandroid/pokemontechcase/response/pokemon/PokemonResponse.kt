package com.bedirhandroid.pokemontechcase.response.pokemon


data class PokemonResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonResultModel>
)