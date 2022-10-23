package com.alberto.rickandmortyapp.features.characters.activity

import androidx.activity.viewModels
import com.alberto.rickandmortyapp.core.base.BaseActivityBinding
import com.alberto.rickandmortyapp.databinding.ActivityCharactersBinding
import com.alberto.rickandmortyapp.features.characters.vm.CharactersActivityViewModel

class CharactersActivity : BaseActivityBinding<CharactersActivityViewModel>() {

    private var dataBinding: ActivityCharactersBinding? = null
    override val viewModel: CharactersActivityViewModel by viewModels()

    // Lifecycle
    override fun initOnCreated() {
        dataBinding = ActivityCharactersBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CharactersActivity
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding?.invalidateAll()
        dataBinding = null
    }
}