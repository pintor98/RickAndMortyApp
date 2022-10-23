package com.alberto.rickandmortyapp.features.charcatersDetail.activity

import android.R
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import com.alberto.rickandmortyapp.core.base.BaseActivityBinding
import com.alberto.rickandmortyapp.core.base.Constants.CHARACTER_ID_KEY
import com.alberto.rickandmortyapp.databinding.ActivityCharactersDetailBinding
import com.alberto.rickandmortyapp.features.charcatersDetail.vm.CharactersDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersDetailActivity : BaseActivityBinding<CharactersDetailViewModel>() {

    private var dataBinding: ActivityCharactersDetailBinding? = null
    override val viewModel: CharactersDetailViewModel by viewModels()

    // Lifecycle
    override fun initOnCreated() {
        dataBinding = ActivityCharactersDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CharactersDetailActivity
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getExtras()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding?.invalidateAll()
        dataBinding = null
    }

    // Functions
    private fun getExtras() {
        if (intent.getIntExtra(CHARACTER_ID_KEY, -1) != -1) {
            viewModel.getCharacterById(intent.getIntExtra(CHARACTER_ID_KEY, -1))
        }
    }
}