package com.mimecast.tronalddump.mockserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


internal class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {

            return when {
                request.path == MockServerEndpoints.GET_TAGS -> MockResponse().setResponseCode(200).setBody(
                    MockServerJSONResponses.SUCCESS.TAGS)
                request.path == MockServerEndpoints.GET_TAG_DETAILS -> MockResponse().setResponseCode(200).setBody(
                        MockServerJSONResponses.SUCCESS.TAG_DETAILS)
                else -> MockResponse().setResponseCode(404).setBody(MockServerJSONResponses.FAIL.NOT_FOUND)

            }

        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {

            return when {
                request.path == MockServerEndpoints.GET_TAGS -> MockResponse().setResponseCode(400).setBody(
                    MockServerJSONResponses.FAIL.NOT_FOUND
                )
                else -> MockResponse().setResponseCode(404).setBody(MockServerJSONResponses.FAIL.NOT_FOUND)
            }

        }
    }
}