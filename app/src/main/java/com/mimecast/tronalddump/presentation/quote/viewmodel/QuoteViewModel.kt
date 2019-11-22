package com.mimecast.tronalddump.presentation.quote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mimecast.tronalddump.domain.entities.Quote
import com.mimecast.tronalddump.util.DateFormatter

class QuoteViewModel: ViewModel() {
    var displayDescription = MutableLiveData<String>()
    var displayDate = MutableLiveData<String>()

    fun bind(quote: Quote) {
        displayDescription.value = quote.value

        // Date follows this format "2016-07-10T18:27:58"
        displayDate.value = DateFormatter.convertApiDateToReadable(quote.appeared_at, "yyyy-MM-dd'T'hh:mm:ss", "d MMM yyyy")
    }
}