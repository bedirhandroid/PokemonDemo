package com.bedirhandroid.pokemontechcase.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.pokemontechcase.R
import com.bedirhandroid.pokemontechcase.databinding.ItemNetworkStateBinding
import com.bedirhandroid.pokemontechcase.ui.second.fragments.pokemon.list.PokemonListAdapter

class PokemonLoadingStateAdapter(
    private val adapter: PokemonListAdapter
) : LoadStateAdapter<PokemonLoadingStateAdapter.NetworkStateItemViewHolder>() {

    class NetworkStateItemViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener {
                retryCallback.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            ItemNetworkStateBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_network_state, parent, false)
            )
        ) { adapter.retry() }
}