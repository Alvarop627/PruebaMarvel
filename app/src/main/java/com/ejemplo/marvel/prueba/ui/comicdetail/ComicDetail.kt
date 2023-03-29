package com.ejemplo.marvel.prueba.ui.comicdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.ejemplo.marvel.prueba.databinding.ActivityComicDetailBinding
import com.ejemplo.marvel.prueba.domain.model.ComicModel
import com.ejemplo.marvel.prueba.utils.GlobalConstants

class ComicDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el modelo de comic pasado como argumento
        val comic = intent.getParcelableExtra("comic") as? ComicModel

        if (comic != null) {
            // Mostrar los detalles del comic en la vista
            binding.comicTitle.text = comic?.title ?: ""
            binding.comicDescription.text = comic?.description ?: ""

            // Cargar la imagen del comic utilizando Glide
            val thumbnail = comic.thumbnail?.path.plus(GlobalConstants.PUNTO)
                .plus(comic.thumbnail?.extension)
            val uri = thumbnail.toUri().buildUpon().scheme("https").build()
            Glide.with(this).load(uri).into(binding.comicImage)
        }
    }
}
