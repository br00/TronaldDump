package com.mimecast.tronalddump.domain.repositories.callback

import com.mimecast.tronalddump.data.entities.ApiError

/**
 * Repository listener
 */
interface OnResponseReceived<in T> {
    fun onSuccess(body: T?)
    fun onError(errorCode: ApiError?)
}