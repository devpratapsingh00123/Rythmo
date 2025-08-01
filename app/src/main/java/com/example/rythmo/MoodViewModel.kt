package com.example.rythmo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoodViewModel : ViewModel() {

    private val _selectedMood = MutableLiveData<String>()
    val selectedMood: LiveData<String> get() = _selectedMood

    fun setMood(mood: String) {
        _selectedMood.value = mood
    }
}
