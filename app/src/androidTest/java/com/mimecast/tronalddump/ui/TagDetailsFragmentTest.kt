package com.mimecast.tronalddump.ui

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mimecast.tronalddump.MainActivity
import com.mimecast.tronalddump.base.AppDefine
import com.mimecast.tronalddump.mockserver.MockServerDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mimecast.tronalddump.R


/**
 * This test will check that the UI is displayed correctly
 * after retrieving the data using the MockServer
 */
@RunWith(AndroidJUnit4::class)
class TagDetailsFragmentTest {

    @Rule
    @JvmField
    var iActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java,
            false,
            false)

    private lateinit var iMockWWebServer: MockWebServer

    @Before
    fun setUp() {
        AppDefine.BASE_URL = "http://localhost:8080/"
        iMockWWebServer = MockWebServer()
        iMockWWebServer.start(8080)
    }


    @Test
    fun retrieveTagDetails_withSuccess_displayList() {
        iMockWWebServer.setDispatcher(MockServerDispatcher().RequestDispatcher())
        iActivityTestRule.launchActivity(Intent())

        iActivityTestRule.activity.showTagDetails("President Obama")

        // Check that the views are displayed correctly after retrieving the data and update the viewmodel
        Espresso.onView(withId(R.id.tag_details_loading)).check(matches(not(isDisplayed())))
        Espresso.onView(withId(R.id.quote_list)).check(matches(isDisplayed()))
    }


    @Test
    fun retrieveTagDetails_withFailure_displaySnackbar() {
        iMockWWebServer.setDispatcher(MockServerDispatcher().ErrorDispatcher())
        iActivityTestRule.launchActivity(Intent())

        // Check that the views are displayed correctly
        Espresso.onView(withId(R.id.tag_list_loading)).check(matches(not(isDisplayed())))

        // Snackbar will be display with the error message
        Espresso.onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("No route found")))
    }


    @After
    fun tearDown() {
        AppDefine.BASE_URL = "https://api.tronalddump.io/"
        iMockWWebServer.shutdown()
        // Give some time to the server to shutdown and restart for the next test
        Thread.sleep(2000)
    }

}