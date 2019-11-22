package com.mimecast.tronalddump.domain.repositories

import com.mimecast.tronalddump.data.api.TronaldDumpApiService
import com.mimecast.tronalddump.domain.NetworkModule
import com.mimecast.tronalddump.domain.entities.TagDetails
import com.mimecast.tronalddump.domain.entities.Tags
import com.mimecast.tronalddump.domain.repositories.callback.OnResponseReceived

/**
 * Repository to retrieve tags, tag details and quotes
 */
class TagRepository(private val iApiService: TronaldDumpApiService): BaseRepository() {

    fun getTags(callBack: OnResponseReceived<Tags>) {
        iApiService.getTagApi(NetworkModule.retrofit()).getTags().makeCall {
            onResponseSuccess = { callBack.onSuccess(it.body() as Tags) }
            onResponseFailure = { callBack.onError(it)}
        }
    }

    fun getTagDetails(tag: String, callBack: OnResponseReceived<TagDetails>) {
        iApiService.getTagApi(NetworkModule.retrofit()).getTagDetails(tag).makeCall {
            onResponseSuccess = { callBack.onSuccess(it.body() as TagDetails) }
            onResponseFailure = { callBack.onError(it)}
        }
    }
}