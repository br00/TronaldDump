package com.mimecast.tronalddump.util

import java.text.SimpleDateFormat

object DateFormatter {

    fun convertApiDateToReadable(aDateString: String, formatIn: String, formatOut: String): String {
        val dFormatIn = SimpleDateFormat(formatIn)
        val date = dFormatIn.parse(aDateString)
        val dFormatOut = SimpleDateFormat(formatOut)
        return dFormatOut.format(date!!)
    }

}