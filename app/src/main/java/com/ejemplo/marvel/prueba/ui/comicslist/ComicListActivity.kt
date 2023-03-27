package com.ejemplo.marvel.prueba.ui.comicslist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ejemplo.marvel.prueba.databinding.ActivityComicsListBinding
import com.ejemplo.marvel.prueba.domain.model.CharacterModel
import com.ejemplo.marvel.prueba.domain.model.ComicModel
import com.ejemplo.marvel.prueba.ui.comicslist.adapter.ComicListAdapter
import com.ejemplo.marvel.prueba.ui.comicslist.viewmodel.ComicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicListActivity : AppCompatActivity() {

    private var id: String = ""
    private lateinit var binding: ActivityComicsListBinding
    private lateinit var adapter: ComicListAdapter
    private lateinit var comicViewModel: ComicViewModel

    private var comicList: ArrayList<ComicModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.comicsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerViewComics()
        comicViewModel = ViewModelProvider(this).get(ComicViewModel::class.java)
        observeViewmodel()
        if (intent != null) {
            id = intent.getStringExtra("id").toString()
            comicViewModel.getComicsListByCharacterId(id)
            comicViewModel.comicListState.value?.comicList?.let { comicList.addAll(it) }
        }

    }

    private fun observeViewmodel() {
        comicViewModel.comicListState.observe(this) { comicListState ->
            when {
                comicListState.isLoading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                comicListState.error.isNotBlank() -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, comicListState.error, Toast.LENGTH_LONG)
                        .show()
                }
                comicListState.comicList.isNotEmpty() -> {
                    binding.progressCircular.visibility = View.GONE
                    adapter.itemList.clear()
                    adapter.setData(comicListState.comicList as ArrayList<ComicModel>)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewComics() {
        adapter = ComicListAdapter(this, ArrayList())
        binding.comicsRecyclerView.adapter = adapter
    }
}