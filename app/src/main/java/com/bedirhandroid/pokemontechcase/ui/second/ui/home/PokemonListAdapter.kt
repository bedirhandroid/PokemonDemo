package com.bedirhandroid.pokemontechcase.ui.second.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.pokemontechcase.databinding.RecyclerRowBinding
import com.bedirhandroid.pokemontechcase.response.pokemon.PokemonResultModel


class PokemonListAdapter(val clickItem: (Int) -> Unit): PagingDataAdapter<PokemonResultModel,PokemonListAdapter.PokemonListVH>(POKEMON_COMPARATOR){


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PokemonListVH {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return PokemonListVH(binding)
    }

    override fun onBindViewHolder(holder: PokemonListVH, position: Int) {
        holder.bind(getItem(position)?.name)
        holder.binding.tvPokemonName.setOnClickListener { clickItem.invoke(position) }
    }

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<PokemonResultModel>() {
            override fun areItemsTheSame(
                oldItem: PokemonResultModel,
                newItem: PokemonResultModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PokemonResultModel,
                newItem: PokemonResultModel
            ): Boolean {
                return oldItem == newItem
            }
        }
}

    class PokemonListVH(val binding: RecyclerRowBinding,) : RecyclerView.ViewHolder(binding.root) {
        fun bind(path: String?) {
            path?.let {
                binding.tvPokemonName.text = path
            }
        }
    }
}

