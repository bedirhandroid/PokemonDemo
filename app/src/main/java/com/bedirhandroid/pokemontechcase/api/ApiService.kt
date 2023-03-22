package com.bedirhandroid.pokemontechcase.api

import com.bedirhandroid.pokemontechcase.response.pokemon.listmodels.PokemonResponse
import com.bedirhandroid.pokemontechcase.response.pokemon.detailmodels.PokemonDetailResponse
import com.bedirhandroid.pokemontechcase.util.Constant.STATIC_LIMIT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    //pokemon request with dynamic offset
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit : Int? = STATIC_LIMIT
    ) : PokemonResponse

    //pokemon detail request with dynamic path
    @GET("pokemon/{subUrl}")
    suspend fun pokemonDetails(@Path (value = "subUrl") subUrl: Int): PokemonDetailResponse
}