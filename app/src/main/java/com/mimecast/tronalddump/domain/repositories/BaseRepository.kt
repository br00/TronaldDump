package com.mimecast.tronalddump.domain.repositories

import com.mimecast.tronalddump.domain.repositories.callback.RetryableCallback
import retrofit2.Call

/**
 * Base repository class made using
 * Kotlin Higher-Order-Function, which accepts RetryableCallback of any type as a function
 * via lambda expression and invoke enqueue on the Call object of any response type.
 */
open class BaseRepository {
    fun<T> Call<T>.makeCall(callback: RetryableCallback<T>.() -> Unit) {
        val retryableCallback = RetryableCallback<T>(null)
        callback.invoke(retryableCallback)
        this.enqueue(retryableCallback)
    }
}