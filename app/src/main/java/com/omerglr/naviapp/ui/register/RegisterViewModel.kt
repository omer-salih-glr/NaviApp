package com.omerglr.naviapp.ui.mesaj

import android.widget.NumberPicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    /*private val _cinsiyet = MutableLiveData<String>().apply {
        value = "Kadın"
        value = "Erkek"
        value = "Diğer"
    }*/


    /*
    val number = arrayOf("Kadın", "Erkek", "Diğer")
    number_picker.minValue = 0
    number_picker.maxValue = number.size - 1
    number_picker.displayedValues = number
*/


}