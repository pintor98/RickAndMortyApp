package com.alberto.rickandmortyapp.features.characters.activity

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.alberto.rickandmortyapp.core.base.BaseActivityBinding
import com.alberto.rickandmortyapp.core.base.Constants.CHARACTER_ID_KEY
import com.alberto.rickandmortyapp.core.extensions.observe
import com.alberto.rickandmortyapp.core.extensions.startActivity
import com.alberto.rickandmortyapp.databinding.ActivityCharactersBinding
import com.alberto.rickandmortyapp.features.characters.adapter.CharactersRecyclerViewAdapter
import com.alberto.rickandmortyapp.features.characters.vm.CharactersActivityViewModel
import com.alberto.rickandmortyapp.features.charcatersDetail.activity.CharactersDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersActivity : BaseActivityBinding<CharactersActivityViewModel>(), CharactersRecyclerViewAdapter.OnItemClickListener {

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

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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
        adapter = CharactersRecyclerViewAdapter(this)
        dataBinding?.charactersRecyclerView?.layoutManager = GridLayoutManager(this, 2)
        dataBinding?.charactersRecyclerView?.adapter = adapter
    }

    override fun onItemClick(id: Int) {
        startActivity<CharactersDetailActivity>(CHARACTER_ID_KEY to id)
    }
}