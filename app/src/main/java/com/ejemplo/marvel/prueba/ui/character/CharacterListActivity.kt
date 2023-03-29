package com.ejemplo.marvel.prueba.ui.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ejemplo.marvel.prueba.databinding.ActivityMainBinding
import com.ejemplo.marvel.prueba.domain.model.CharacterModel
import com.ejemplo.marvel.prueba.ui.character.viewmodel.CharacterViewModel
import com.ejemplo.marvel.prueba.ui.character.adapter.CharacterListAdapter
import com.ejemplo.marvel.prueba.utils.GlobalConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterListAdapter
    private lateinit var characterViewModel: CharacterViewModel

    private val idsList: ArrayList<String> = arrayListOf(
        GlobalConstants.IRON_MAN_ID,
        GlobalConstants.HULK_ID,
        GlobalConstants.THOR_ID,
        GlobalConstants.CAPTAIN_AMERICA_ID
    )
    private var charactersList: ArrayList<CharacterModel> = arrayListOf()

    var list = arrayListOf<CharacterModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.characterRecyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerViewCharacters()

        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        observeViewmodel()
        callCharactersList()
    }

    private fun observeViewmodel() {
        characterViewModel.characterState.observe(this) { characterState ->
            when {
                characterState.isLoading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                characterState.error.isNotBlank() -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, characterState.error, Toast.LENGTH_LONG)
                        .show()
                }
                characterState.marvelCharacter.isNotEmpty() -> {
                    binding.progressCircular.visibility = View.GONE
                }
            }
        }
        characterViewModel.thorValue.observe(this) { characterValue ->
            if (characterValue.marvelCharacter.isNotEmpty()) {
                binding.progressCircular.visibility = View.GONE
                adapter.itemList.clear()
                addCharacterToList(characterValue.marvelCharacter[0])
            }
        }
        characterViewModel.ironManValue.observe(this) { characterValue ->
            if (characterValue.marvelCharacter.isNotEmpty()) {
                binding.progressCircular.visibility = View.GONE
                adapter.itemList.clear()
                addCharacterToList(characterValue.marvelCharacter[0])
            }
        }
        characterViewModel.hulkValue.observe(this) { characterValue ->
            if (characterValue.marvelCharacter.isNotEmpty()) {
                binding.progressCircular.visibility = View.GONE
                adapter.itemList.clear()
                addCharacterToList(characterValue.marvelCharacter[0])
            }
        }
        characterViewModel.captainAmericaValue.observe(this) { characterValue ->
            if (characterValue.marvelCharacter.isNotEmpty()) {
                binding.progressCircular.visibility = View.GONE
                adapter.itemList.clear()
                addCharacterToList(characterValue.marvelCharacter[0])
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewCharacters() {
        adapter = CharacterListAdapter(this, ArrayList())
        binding.characterRecyclerView.adapter = adapter
    }

    private fun callCharactersList() {
        for (id in idsList) {
            characterViewModel.getCharacterByIdValue(id)
        }
    }

    private fun addCharacterToList(characterModel: CharacterModel) {
        if (!this.charactersList.contains(characterModel)) {
            this.charactersList.add(characterModel)
            this.charactersList.sortBy { it.name }
            adapter.setData(this.charactersList)
        }
    }

}