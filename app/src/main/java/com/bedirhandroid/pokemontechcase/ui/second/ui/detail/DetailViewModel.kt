package com.bedirhandroid.pokemontechcase.ui.second.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.pokemontechcase.base.BaseViewModel
import com.bedirhandroid.pokemontechcase.base.ErrorMessages
import com.bedirhandroid.pokemontechcase.base.Repo
import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {

    private val mutablePokemonLiveData = MutableLiveData<PokemonDetailResponse>()
    val pokemonLiveData : LiveData<PokemonDetailResponse> get() = mutablePokemonLiveData

    fun getPokemonDetails(id: Int) {
        sendRequest {
            repo.pokemonDetail(id).collectLatest {
                it?.let (mutablePokemonLiveData::postValue) ?: kotlin.run {
                    errorLiveData.postValue(ErrorMessages.ERROR)
                }
            }
        }
    }
}