package com.bedirhandroid.pokemontechcase.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bedirhandroid.pokemontechcase.api.ApiService
import com.bedirhandroid.pokemontechcase.paging.PokemonPagingAdapter
import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonDetailResponse
import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo @Inject constructor(private val apiService : ApiService) {

    fun getPokemonList() : Flow<PagingData<PokemonResultModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {PokemonPagingAdapter(apiService)}
        ).flow
    }

    suspend fun pokemonDetail(subUrl : Int): Flow<PokemonDetailResponse?> {
        return flow {
            emit(apiService.pokemonDetails(subUrl))
        }
    }
}