package com.mimecast.tronalddump.data.api

import com.mimecast.tronalddump.data.api.interfaces.TagApi
import retrofit2.Retrofit

/**
 * REST API access points
 */
object TronaldDumpApiService {

    fun getTagApi(retrofit: Retrofit): TagApi {
        return retrofit.create(TagApi::class.java)
    }

}