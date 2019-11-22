package com.mimecast.tronalddump.presentation.tag.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TagViewModel: ViewModel() {
    var displayTag = MutableLiveData<String>()

    fun bind(tag: String) {
        displayTag.value = tag
    }
}