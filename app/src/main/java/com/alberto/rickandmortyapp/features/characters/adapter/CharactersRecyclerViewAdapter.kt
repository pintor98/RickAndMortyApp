package com.alberto.rickandmortyapp.features.characters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alberto.rickandmortyapp.R
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.google.android.material.textview.MaterialTextView

class CharactersRecyclerViewAdapter: PagingDataAdapter<CharacterModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val charactersViewHolder = holder as CharactersViewHolder

        val character = getItem(position)

        if (character != null) {
            charactersViewHolder.name.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = CharactersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item_view, parent, false))

    class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: MaterialTextView = itemView.findViewById(R.id.character_item_view_name)
    }
}