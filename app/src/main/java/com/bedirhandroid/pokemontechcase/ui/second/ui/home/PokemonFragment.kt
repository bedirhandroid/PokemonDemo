package com.bedirhandroid.pokemontechcase.ui.second.ui.home

import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.bedirhandroid.pokemontechcase.R
import com.bedirhandroid.pokemontechcase.base.BaseFragment
import com.bedirhandroid.pokemontechcase.databinding.FragmentPokemonBinding
import com.bedirhandroid.pokemontechcase.paging.PokemonLoadingStateAdapter
import com.bedirhandroid.pokemontechcase.util.checkConnection
import com.bedirhandroid.pokemontechcase.util.gone
import com.bedirhandroid.pokemontechcase.util.navigateWithBundleTo
import com.bedirhandroid.pokemontechcase.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFragment : BaseFragment<FragmentPokemonBinding, PokemonViewModel>() {

    private var pokemonListAdapter: PokemonListAdapter? = null

    override fun initView() {
        checkConnectionView()
        pokemonListAdapter = PokemonListAdapter(::onClickAdapter)
        binding.rvPokemonList.adapter =
            pokemonListAdapter?.withLoadStateFooter(
                footer = PokemonLoadingStateAdapter(pokemonListAdapter!!)
            )
    }

    private fun checkConnectionView() {
        viewBindingScope {
            when(checkConnection(requireContext())) {
                true -> {
                    notConnectionContainer.gone()
                    rvPokemonList.visible()
                }
                else -> {
                    notConnectionContainer.visible()
                    rvPokemonList.gone()
                }
            }
        }
    }

    override fun initListeners() {
        viewBindingScope {
            btnNotConnection.setOnClickListener {
                pokemonListAdapter?.refresh()
                checkConnectionView()
            }
        }
    }

    override fun initObservers() {
        viewModelScope {
            viewLifecycleOwner.lifecycleScope.launch {
                getPokemonList().collectLatest { _data ->
                    pokemonListAdapter?.submitData(_data)
                }
            }
        }
    }

    private fun onClickAdapter(position: Int) {
        navigateWithBundleTo(R.id.action_nav_home_to_detailFragment,
        bundleOf("data" to position+1))
    }
}