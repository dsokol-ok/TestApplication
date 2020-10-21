package com.example.testapplication.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _text = MutableLiveData<Int>()
    val text: LiveData<Int> = _text

    init {
        _text.value = 0
    }

    fun plusOne() {
        _text.value = _text.value?.plus(1)
    }

    fun minusOne(){
        if (checkValue()) {
            _text.value = _text.value?.minus(1)
        }
    }

    private fun checkValue(): Boolean{
        return (_text.value!! > 0)
    }
}