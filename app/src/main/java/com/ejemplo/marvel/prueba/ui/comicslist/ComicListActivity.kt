package com.ejemplo.marvel.prueba.ui.comic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ejemplo.marvel.prueba.databinding.ActivityComicsListBinding
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
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.comicsRecyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerViewComics()

        comicViewModel = ViewModelProvider(this).get(ComicViewModel::class.java)

        observeViewmodel()
        cargar()

        binding.comicsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.comicsRecyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (!isLoading && !isLastPage && lastVisibleItemPosition == totalItemCount - 1) {
                    currentPage += 1
                    loadMoreItems()
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewmodel() {
        comicViewModel.comicListState.observe(this) { comicListState ->
            when {
                comicListState.isLoading -> {
                    isLoading = true
                    binding.progressCircular.visibility = View.VISIBLE
                }
                comicListState.error.isNotBlank() -> {
                    isLoading = false
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, comicListState.error, Toast.LENGTH_LONG)
                        .show()
                }
                comicListState.comicList.isNotEmpty() -> {
                    isLoading = false
                    binding.progressCircular.visibility = View.GONE
                    if (comicListState.comicList.isNotEmpty() && !comicList.containsAll(
                            comicListState.comicList
                        )
                    ) {
                        comicList.addAll(comicListState.comicList)
                        adapter.itemList.addAll(comicListState.comicList)
                        adapter.notifyDataSetChanged()
                    }
                    if (comicListState.comicList.size < 20) {
                        isLastPage = true
                    }
                }
            }
        }
    }

    private fun recyclerViewComics() {
        adapter = ComicListAdapter(this, ArrayList())
        binding.comicsRecyclerView.adapter = adapter
    }

    private fun cargar() {
        if (intent != null) {
            id = intent.getStringExtra("id").toString()
            comicViewModel.getComicListByCharacterId(id, currentPage * 20)
        }
    }

    private fun loadMoreItems() {
        comicViewModel.getComicListByCharacterId(id, currentPage * 20)
    }
}