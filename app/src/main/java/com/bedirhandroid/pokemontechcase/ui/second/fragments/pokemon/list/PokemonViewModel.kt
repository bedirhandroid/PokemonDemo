package com.bedirhandroid.pokemontechcase.ui.second.fragments.pokemon.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bedirhandroid.pokemontechcase.base.BaseViewModel
import com.bedirhandroid.pokemontechcase.base.Repo
import com.bedirhandroid.pokemontechcase.response.pokemon.listmodels.PokemonResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {

    fun getPokemonList(): Flow<PagingData<PokemonResultModel>> {
        return repo.getPokemonList().cachedIn(viewModelScope)
    }
}