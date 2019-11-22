package com.mimecast.tronalddump.ui.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mimecast.tronalddump.presentation.tag.viewmodel.TagListViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.rules.TestRule
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TagListViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var iViewModel: TagListViewModel


    @Before
    fun setUp() {
        iViewModel = TagListViewModel()
    }


    @Test
    fun updateViewModel() {
        iViewModel.onRetrieveListFinish()

        // Check that the viewmodel is updated as expected
        assertEquals(iViewModel.loadingVisibility.value, View.GONE)
        assertEquals(iViewModel.errorMessage.value, null)

        // Mock the data
        val tagList = listOf("President Obama", "Hillary Clinton")
        iViewModel.listAdapter.updateList(tagList)
        val tag = iViewModel.listAdapter.getList()[0]
        assertEquals(tag, "President Obama")
    }

}
