package com.mimecast.tronalddump.presentation.tag.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mimecast.tronalddump.data.api.TronaldDumpApiService
import com.mimecast.tronalddump.data.entities.ApiError
import com.mimecast.tronalddump.domain.entities.Tags
import com.mimecast.tronalddump.domain.repositories.callback.OnResponseReceived
import com.mimecast.tronalddump.domain.repositories.TagRepository
import com.mimecast.tronalddump.presentation.tag.TagListAdapter
import com.mimecast.tronalddump.util.SingleLiveEvent


class TagListViewModel: ViewModel(), TagListAdapter.ItemListenerAdapter {

    val TAG = "TagListViewModel"

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isRefreshing: ObservableBoolean = ObservableBoolean()
    var listAdapter: TagListAdapter = TagListAdapter(this)
    val showTagDetailsEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadTags() }

    init {
        loadTags()
    }

    fun loadTags() {
        onRetrieveListStart()
        TagRepository(TronaldDumpApiService).getTags(object : OnResponseReceived<Tags> {
            override fun onSuccess(body: Tags?) {
                Log.d(TAG, "response received")
                onRetrieveListFinish()
                listAdapter.updateList(body?._embedded!!)
            }

            override fun onError(errorCode: ApiError?) {
                Log.d(TAG, "error: " + errorCode?.message)
                onRetrieveListFinish()
                // Update the UI and show the error
                errorMessage.value = errorCode?.message
            }
        })
    }

    fun onRetrieveListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun onRetrieveListFinish() {
        loadingVisibility.value = View.GONE
        isRefreshing.set(false)
    }

    fun onRefreshList() {
        isRefreshing.set(true)
        loadTags()
    }

    override fun onItemClick(tag: String) {
        showTagDetailsEvent.value = tag
        showTagDetailsEvent.call()
    }
}