package com.ejemplo.marvel.prueba.ui.character.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejemplo.marvel.prueba.R
import com.ejemplo.marvel.prueba.domain.model.CharacterModel
import com.ejemplo.marvel.prueba.ui.comicslist.ComicListActivity
import com.ejemplo.marvel.prueba.utils.GlobalConstants
import kotlinx.coroutines.*

import java.util.*
import kotlin.collections.ArrayList

class CharacterListAdapter(private val context: Context, var itemList:ArrayList<CharacterModel>) : RecyclerView.Adapter<
        CharacterListAdapter.CharacterListViewHolder>() {
    inner class CharacterListViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val characterName : TextView = view.findViewById(R.id.txtCharacterName)
        val thumbnail : ImageView = view.findViewById(R.id.imgCharacterImage)
        val cardCharacter : LinearLayout = view.findViewById(R.id.charactersLinearLayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_character_grid,parent,false)
        return CharacterListViewHolder(view)
    }
    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val itemSelected = itemList[position]
        holder.characterName.text = itemSelected.name
        val imageUrl = itemList[position].thumbnail.plus(GlobalConstants.PUNTO).plus(itemList[position].thumbnailExt)
        val uri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context).load(uri).into(holder.thumbnail)
        holder.cardCharacter.setOnClickListener{
            val intent = Intent(context, ComicListActivity::class.java)
            intent.putExtra("id",itemSelected.id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(characterList:ArrayList<CharacterModel>)
    {
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }
}