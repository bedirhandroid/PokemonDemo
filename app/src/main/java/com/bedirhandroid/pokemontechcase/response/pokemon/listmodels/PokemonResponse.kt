package com.bedirhandroid.pokemontechcase.response.pokemon.listmodels


//data class for Pokemon List request
data class PokemonResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonResultModel>
)