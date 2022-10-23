package com.alberto.rickandmortyapp.features.charcatersDetail.activity

import android.R
import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.alberto.rickandmortyapp.core.base.BaseActivityBinding
import com.alberto.rickandmortyapp.core.base.Constants.CHARACTER_ID_KEY
import com.alberto.rickandmortyapp.core.extensions.observe
import com.alberto.rickandmortyapp.databinding.ActivityCharactersDetailBinding
import com.alberto.rickandmortyapp.features.charcatersDetail.vm.CharactersDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import com.alberto.rickandmortyapp.R as res


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

    @SuppressLint("SetTextI18n")
    override fun observerViewModels() {
        observe(viewModel.character) { character ->
            dataBinding?.let { binding ->
                binding.apply {
                    Glide
                        .with(this@CharactersDetailActivity)
                        .load(character.image)
                        .into(characterImage)

                    characterName.text = "Name: ${character.name}"
                    characterGender.text = "Gender: ${character.gender}"
                    characterSpecies.text = "Species: ${character.species}"
                    characterType.text = "Type: ${character.type}"

                    if (character.status == "Alive") {
                        characterState.setTextColor(ContextCompat.getColor(this@CharactersDetailActivity, res.color.green))
                    } else if (character.status == "Dead") {
                        characterState.setTextColor(ContextCompat.getColor(this@CharactersDetailActivity, res.color.red))
                    }

                    characterState.text = character.status
                }
            }
        }
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