package com.bedirhandroid.pokemontechcase.response.pokemon.detailmodels

// Data class for Pokemon detail request
data class PokemonDetailResponse(
    val height: Int? = null,
    val name: String? = null,
    val weight: Int? = null,
    val sprites: Sprites? = null
)