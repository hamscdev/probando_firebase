package com.devnight.pruebafirebase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnight.pruebafirebase.domain.usescase.GetRecomendationUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewMainModel @Inject constructor(
    private val usesCase: GetRecomendationUsesCase) : ViewModel(){


    fun onCreate(){
        viewModelScope.launch {

            val result = usesCase.getRecomendations()
            Log.e("resultado", result.toString())

        }
    }

}