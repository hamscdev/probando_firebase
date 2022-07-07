package com.devnight.pruebafirebase.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devnight.pruebafirebase.core.adapters.MoviesAdapter
import com.devnight.pruebafirebase.databinding.ActivityMainBinding
import com.devnight.pruebafirebase.ui.viewmodel.ViewMainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Ma1inActivity: AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewMainModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        viewModel.onCreate()
        viewModel.responses.observe(this, Observer {
            binding.listRecomendations.layoutManager = LinearLayoutManager(this)
            binding.listRecomendations.adapter = MoviesAdapter(it, this)
        })



        binding.buttonMaps.setOnClickListener {
            val intent = Intent(this, Navigation::class.java)
            startActivity(intent)
        }





    }
}