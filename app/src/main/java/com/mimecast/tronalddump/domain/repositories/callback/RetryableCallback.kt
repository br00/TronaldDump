package com.mimecast.tronalddump.domain.repositories.callback

import android.util.Log
import com.google.gson.Gson
import com.mimecast.tronalddump.data.entities.ApiError
import com.mimecast.tronalddump.util.HttpCallFailureException
import com.mimecast.tronalddump.util.NoNetworkException
import com.mimecast.tronalddump.util.ServerUnreachableException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Custom retryable Retrofit Callback to handle the common response and errors.
 */
class RetryableCallback<T>(private val call: Call<T>?) : Callback<T> {

    val TAG = "RetryableCallback"

    var onResponseSuccess: ((Response<T>) -> Unit)? = null
    var onResponseFailure: ((error: ApiError?) -> Unit)? = null

    /* This is only called in specific scenario, like no internet, host name not found etc, so no specific error code */
    override fun onFailure(call: Call<T>, t: Throwable) {
        // Here we need to parse the Throwable to find out the type of error
        // because api doesn't return a response
        lateinit var apiError: ApiError
        when (t) {
            is NoNetworkException -> apiError = ApiError(1, "No network :(")
            is ServerUnreachableException -> apiError = ApiError(2, "The Server is down")
            is HttpCallFailureException -> apiError = ApiError(3, "An error occurred while retrieving the data")
            else -> apiError = ApiError(4, "Something went wrong")
        }
        onResponseFailure?.invoke(apiError)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {  // code >= 200 && code < 300
            if (response.code() == 200) {
                Log.d(TAG, "response.code() == 200")
                onResponseSuccess?.invoke(response)
            }

        } else {
            // Common error handling for all the responses
            val errorBody = response.errorBody()
            errorBody?.let {
                val reader = errorBody.charStream()
                if (null != reader) {
                    val errorResponseBody = Gson().fromJson<ApiError>(reader, ApiError::class.java)
                    if (null != errorResponseBody) {
                        onResponseFailure?.invoke(errorResponseBody)
                    } else {
                        onResponseFailure?.invoke(ApiError(4, "Something went wrong"))
                    }
                }
            }
        }
    }

    fun retryCall() {
        // Retry mechanism not implemented
        call?.clone()?.enqueue(this)
    }
}