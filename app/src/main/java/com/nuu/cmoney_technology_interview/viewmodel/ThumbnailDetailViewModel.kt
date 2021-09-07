package com.nuu.cmoney_technology_interview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThumbnailDetailViewModel: ViewModel() {
    private val _clickEvent = MutableLiveData<String>()

    val clickEvent : LiveData<String>
        get() = _clickEvent

    fun userClicksOnButton() {
        _clickEvent.value = null
    }
}