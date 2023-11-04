package com.example.screen.ui.main.ui.ingresos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngresosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ingresos Fragment"
    }
    val text: LiveData<String> = _text
}