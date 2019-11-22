package com.mimecast.tronalddump.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mimecast.tronalddump.base.AppDefine
import com.mimecast.tronalddump.data.api.TronaldDumpApiService
import com.mimecast.tronalddump.domain.NetworkModule
import com.mimecast.tronalddump.mockserver.MockServerDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Class to test the network service, api interfaces
 */
@RunWith(AndroidJUnit4::class)
class TronaldDumpApiServiceTest {


    private lateinit var iMockWWebServer: MockWebServer


    @Before
    fun createService() {
        AppDefine.BASE_URL = "http://localhost:8080/"
        iMockWWebServer = MockWebServer()
        iMockWWebServer.start(8080)
    }


    @Test
    fun makeRequest_apiServiceAndNetworkModule_notNull() {
        iMockWWebServer.setDispatcher(MockServerDispatcher().RequestDispatcher())

        Assert.assertNotNull(TronaldDumpApiService)
        Assert.assertNotNull(NetworkModule)
    }


    @After
    fun stopService() {
        AppDefine.BASE_URL = "https://api.tronalddump.io/"
        iMockWWebServer.shutdown()
        // Give some time to the server to shutdown and restart for the next test
        Thread.sleep(2000)
    }
}