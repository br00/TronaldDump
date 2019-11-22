package com.mimecast.tronalddump.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mimecast.tronalddump.base.AppDefine
import com.mimecast.tronalddump.data.api.TronaldDumpApiService
import com.mimecast.tronalddump.data.entities.ApiError
import com.mimecast.tronalddump.domain.NetworkModule
import com.mimecast.tronalddump.domain.entities.Tags
import com.mimecast.tronalddump.domain.repositories.BaseRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResponseTest {


    private inner class MockRepository: BaseRepository() {

        var responseError: ApiError? = null
        var responseSuccess: Tags? = null

        fun makeRequest() {
            val api = TronaldDumpApiService
            val retrofit = NetworkModule.retrofit()

            api.getTagApi(retrofit).getTags().makeCall {
                onResponseSuccess = {
                    responseSuccess = (it.body() as Tags)
                }
                onResponseFailure = {
                    responseError = it!!
                }
            }
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun makeRequest_withFailure_receiveApiError() {

        AppDefine.BASE_URL = "https://fakeurltofailcall"

        val mock = MockRepository()
        mock.makeRequest()

        Thread.sleep(500)

        Assert.assertNotNull(mock.responseError)
        Assert.assertNull(mock.responseSuccess)

        Assert.assertEquals(4, mock.responseError?.status)
        Assert.assertEquals("Something went wrong", mock.responseError?.message)
    }


    fun makeRequest_withSuccess_receiveTags() {
        //TODO
    }

}