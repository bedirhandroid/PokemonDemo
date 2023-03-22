package com.bedirhandroid.pokemontechcase.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bedirhandroid.pokemontechcase.api.ApiService
import com.bedirhandroid.pokemontechcase.response.pokemon.listmodels.PokemonResultModel
import com.bedirhandroid.pokemontechcase.util.Constant.START_OFFSET
import java.io.IOException
import javax.inject.Inject

//Default Paging Adapter
class PokemonPagingAdapter @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, PokemonResultModel>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonResultModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResultModel> {
        val position = params.key ?: START_OFFSET
        return try {
            val response = apiService.getPokemonList(position)
            val list = response.results
            LoadResult.Page(
                data = list,
                prevKey = if (position == START_OFFSET) null else position,
                nextKey = if (list.isEmpty()) null else position + 20
            )
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        }
    }

}