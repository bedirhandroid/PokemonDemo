package com.bedirhandroid.pokemontechcase.api

import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonResponse
import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonDetailResponse
import com.bedirhandroid.pokemontechcase.util.Constant.START_OFFSET
import com.bedirhandroid.pokemontechcase.util.Constant.STATIC_LIMIT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit : Int? = STATIC_LIMIT
    ) : PokemonResponse

    @GET("pokemon/{subUrl}")
    suspend fun pokemonDetails(@Path (value = "subUrl") subUrl: Int): PokemonDetailResponse
}