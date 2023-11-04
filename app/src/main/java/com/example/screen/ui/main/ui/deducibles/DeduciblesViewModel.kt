package com.example.screen.ui.main.ui.deducibles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeduciblesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Deducibles Fragment"
    }
    val text: LiveData<String> = _text
}