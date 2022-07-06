package com.devnight.pruebafirebase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnight.pruebafirebase.domain.model.RecomendationModel
import com.devnight.pruebafirebase.domain.usescase.GetRecomendationUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewMainModel @Inject constructor(
    private val usesCase: GetRecomendationUsesCase) : ViewModel(){



    val responses = MutableLiveData<List<RecomendationModel>>()


    fun onCreate(){
        viewModelScope.launch {
            val result = usesCase.invoke()
            if (result.isNotEmpty()){
                responses.postValue(result)
            }
        }
    }

}