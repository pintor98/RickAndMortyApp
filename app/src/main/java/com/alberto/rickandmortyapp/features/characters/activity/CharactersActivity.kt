package com.alberto.rickandmortyapp.features.characters.activity

import android.util.Log
import androidx.activity.viewModels
import com.alberto.rickandmortyapp.core.base.BaseActivityBinding
import com.alberto.rickandmortyapp.core.extensions.observe
import com.alberto.rickandmortyapp.databinding.ActivityCharactersBinding
import com.alberto.rickandmortyapp.features.characters.vm.CharactersActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : BaseActivityBinding<CharactersActivityViewModel>() {

    private var dataBinding: ActivityCharactersBinding? = null
    override val viewModel: CharactersActivityViewModel by viewModels()

    // Lifecycle
    override fun initOnCreated() {
        dataBinding = ActivityCharactersBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CharactersActivity
        }

        getAllCharacters()
    }

    override fun observerViewModels() {
        observe(viewModel.characters) { characters ->
            Log.e("CHARACTERS", characters.size.toString())
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
}