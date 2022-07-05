package com.devnight.pruebafirebase.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.devnight.pruebafirebase.R
import com.devnight.pruebafirebase.databinding.ActivityMainBinding
import com.devnight.pruebafirebase.ui.viewmodel.ViewMainModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Ma1inActivity: AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewMainModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.onCreate()





    }
}