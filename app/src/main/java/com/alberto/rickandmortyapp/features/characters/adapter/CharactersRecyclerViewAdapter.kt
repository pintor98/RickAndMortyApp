package com.alberto.rickandmortyapp.features.characters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alberto.rickandmortyapp.R
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class CharactersRecyclerViewAdapter(
    private val listener: OnItemClickListener,
): PagingDataAdapter<CharacterModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

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

            Glide
                .with(charactersViewHolder.itemView)
                .load(character.image)
                .into(charactersViewHolder.image)

            charactersViewHolder.bind(character.id, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = CharactersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item_view, parent, false))

    class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: MaterialTextView = itemView.findViewById(R.id.character_item_view_name)
        val image: ShapeableImageView = itemView.findViewById(R.id.character_item_view_image)

        fun bind(id: Int, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(id) }
        }
    }
}