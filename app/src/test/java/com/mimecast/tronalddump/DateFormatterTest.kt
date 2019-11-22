package com.mimecast.tronalddump

import com.mimecast.tronalddump.util.DateFormatter
import org.junit.Assert
import org.junit.Test

class DateFormatterTest {

    @Test
    fun isDateFormatCorrect() {
        val formattedDate = DateFormatter.convertApiDateToReadable("2016-07-10T18:27:58", "yyyy-MM-dd'T'hh:mm:ss", "d MMM yyyy")
        val expectedDate = "10 Jul 2016"
        Assert.assertEquals(expectedDate, formattedDate)
    }
}