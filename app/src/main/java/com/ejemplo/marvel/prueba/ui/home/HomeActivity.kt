package com.ejemplo.marvel.prueba.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ejemplo.marvel.prueba.R
import com.ejemplo.marvel.prueba.databinding.ActivityHomeBinding
import com.ejemplo.marvel.prueba.ui.character.CharacterListActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setBackgroundResource(R.drawable.marvel)
        binding.button.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.button.setTextColor(ContextCompat.getColor(this, R.color.black))
        setListeners()
    }

    private fun setListeners() {
        binding.button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CharacterListActivity::class.java)
            startActivity(intent)
        })
    }
}