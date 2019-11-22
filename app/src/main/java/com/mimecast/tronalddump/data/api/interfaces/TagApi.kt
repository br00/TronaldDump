package com.mimecast.tronalddump.data.api.interfaces

import com.mimecast.tronalddump.domain.entities.TagDetails
import com.mimecast.tronalddump.domain.entities.Tags
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api definition
 * more info at https://docs.tronalddump.io/#get-tags
 */
interface TagApi {

    @GET("tag")
    fun getTags(): Call<Tags>

    @GET("tag/{tag}")
    fun getTagDetails(
        @Path("tag") tag: String
    ): Call<TagDetails>
}