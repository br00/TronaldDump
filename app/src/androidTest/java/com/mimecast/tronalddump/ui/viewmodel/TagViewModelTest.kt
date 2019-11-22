package com.mimecast.tronalddump.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mimecast.tronalddump.presentation.tag.viewmodel.TagViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.rules.TestRule
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TagViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var iViewModel: TagViewModel


    @Before
    fun setUp() {
        iViewModel = TagViewModel()
    }


    @Test
    fun updateViewModel() {
        // Mock the data
        val tag = "President Obama"
        iViewModel.bind(tag)

        // Check that the viewmodel is updated as expected
        assertEquals(iViewModel.displayTag.value, "President Obama")
    }

}
