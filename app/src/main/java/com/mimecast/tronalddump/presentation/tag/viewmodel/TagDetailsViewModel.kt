package com.mimecast.tronalddump.presentation.tag.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mimecast.tronalddump.data.api.TronaldDumpApiService
import com.mimecast.tronalddump.data.entities.ApiError
import com.mimecast.tronalddump.domain.entities.TagDetails
import com.mimecast.tronalddump.domain.repositories.callback.OnResponseReceived
import com.mimecast.tronalddump.domain.repositories.TagRepository
import com.mimecast.tronalddump.presentation.quote.QuoteListAdapter
import com.mimecast.tronalddump.presentation.tag.TagListAdapter


class TagDetailsViewModel: ViewModel() {

    val TAG = "TagDetailsViewModel"

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isRefreshing: ObservableBoolean = ObservableBoolean()
    var listAdapter: QuoteListAdapter = QuoteListAdapter()

    fun bind(tag: String) {
        loadQuotes(tag)
    }

    private fun loadQuotes(tag: String) {
        onRetrieveListStart()
        TagRepository(TronaldDumpApiService).getTagDetails(tag, object : OnResponseReceived<TagDetails> {
            override fun onSuccess(body: TagDetails?) {
                Log.d(TAG, "response received")
                onRetrieveListFinish()
                listAdapter.updateList(body?._embedded?.tags!!)
            }

            override fun onError(errorCode: ApiError?) {
                Log.d(TAG, "error: " + errorCode?.message)
                onRetrieveListFinish()
                // Update the UI and show the error
            }
        })
    }

    private fun onRetrieveListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveListFinish() {
        loadingVisibility.value = View.GONE
        isRefreshing.set(false)
    }
}