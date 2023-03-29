package com.ejemplo.marvel.prueba.ui.comicslist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejemplo.marvel.prueba.R
import com.ejemplo.marvel.prueba.domain.model.ComicModel
import com.ejemplo.marvel.prueba.ui.comicdetail.ComicDetailActivity
import com.ejemplo.marvel.prueba.utils.GlobalConstants
import kotlinx.coroutines.*
import java.util.*

class ComicListAdapter(private val context: Context, var itemList: ArrayList<ComicModel>) :
    RecyclerView.Adapter<
            ComicListAdapter.ComicListViewHolder>() {
    inner class ComicListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardComic: LinearLayout = view.findViewById(R.id.comicLinearLayout)
        val thumbnail: ImageView = view.findViewById(R.id.imgComicImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_comic_grid, parent, false)
        return ComicListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicListViewHolder, position: Int) {

        val selectedItem = this.itemList[position]
        val thumbnail = selectedItem.thumbnail?.path.plus(GlobalConstants.PUNTO)
            .plus(selectedItem.thumbnail?.extension)
        val uri = thumbnail.toUri().buildUpon().scheme("https").build()
        Glide.with(context).load(uri).into(holder.thumbnail)
        holder.cardComic.setOnClickListener {
            val intent = Intent(context, ComicDetailActivity::class.java)
            intent.putExtra("comic", selectedItem)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(comicList: ArrayList<ComicModel>) {
        this.itemList.addAll(comicList)
        notifyDataSetChanged()
    }
}