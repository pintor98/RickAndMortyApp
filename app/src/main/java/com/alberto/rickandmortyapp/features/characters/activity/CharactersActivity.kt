package com.alberto.rickandmortyapp.features.characters.activity

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.alberto.rickandmortyapp.core.base.BaseActivityBinding
import com.alberto.rickandmortyapp.core.extensions.observe
import com.alberto.rickandmortyapp.databinding.ActivityCharactersBinding
import com.alberto.rickandmortyapp.features.characters.adapter.CharactersRecyclerViewAdapter
import com.alberto.rickandmortyapp.features.characters.vm.CharactersActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersActivity : BaseActivityBinding<CharactersActivityViewModel>() {

    private var dataBinding: ActivityCharactersBinding? = null
    override val viewModel: CharactersActivityViewModel by viewModels()

    lateinit var adapter: CharactersRecyclerViewAdapter

    // Lifecycle
    override fun initOnCreated() {
        dataBinding = ActivityCharactersBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CharactersActivity
        }

        initRecyclerView()
        getAllCharacters()
    }

    override fun observerViewModels() {
        observe(viewModel.characters) { characters ->
            lifecycleScope.launch {
                adapter.submitData(characters)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding?.invalidateAll()
        dataBinding = null
    }

    // Functions
    private fun getAllCharacters() {
        viewModel.getAllCharacters()
    }

    private fun initRecyclerView() {
        adapter = CharactersRecyclerViewAdapter()
        dataBinding?.charactersRecyclerView?.layoutManager = GridLayoutManager(this, 2)
        dataBinding?.charactersRecyclerView?.adapter = adapter
    }
}