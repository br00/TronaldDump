package com.mimecast.tronalddump.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mimecast.tronalddump.domain.entities.Quote
import com.mimecast.tronalddump.presentation.quote.viewmodel.QuoteViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.rules.TestRule
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class QuoteViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var iViewModel: QuoteViewModel


    @Before
    fun setUp() {
        iViewModel = QuoteViewModel()
    }


    @Test
    fun updateViewModel() {
        // Mock Quote data
        val quote = Quote("justAnId", "2016-07-10T18:27:58", "Description quote")
        iViewModel.bind(quote)

        // Check that the viewmodel is updated as expected
        assertEquals(iViewModel.displayDate.value, "10 Jul 2016")
        assertEquals(iViewModel.displayDescription.value,"Description quote")
    }

}
