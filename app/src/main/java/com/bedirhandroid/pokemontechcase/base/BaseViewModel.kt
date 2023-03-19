package com.bedirhandroid.pokemontechcase.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import java.io.EOFException
import java.net.ProtocolException
import java.util.concurrent.Flow
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel(){
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    val showProgress: MutableLiveData<Boolean> = MutableLiveData()


    protected inline fun sendRequest(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            showProgress.postValue(true)
            try {
                block()
            } catch (exception: Exception) {
                when (exception) {
                    is TimeoutException -> errorLiveData.postValue("Zaman Aşımı")
                    is ProtocolException -> errorLiveData.postValue("Tekrar Dene")
                    is EOFException -> errorLiveData.postValue(exception.message)
                    else -> {
                        errorLiveData.postValue(exception.message)
                        Log.e("Exception","${exception.message}",exception)
                    }
                }
            } finally {
                showProgress.postValue(false)
            }
        }
    }
}