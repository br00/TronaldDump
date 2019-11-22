package com.mimecast.tronalddump.presentation.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mimecast.tronalddump.R
import com.mimecast.tronalddump.databinding.ItemQuoteBinding
import com.mimecast.tronalddump.domain.entities.Quote
import com.mimecast.tronalddump.presentation.quote.viewmodel.QuoteViewModel

class QuoteListAdapter: RecyclerView.Adapter<QuoteListAdapter.ViewHolder>() {

    private lateinit var iQuoteList: List<Quote>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemQuoteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_quote, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::iQuoteList.isInitialized) iQuoteList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(iQuoteList[position])
    }

    fun updateList(list: List<Quote>) {
        iQuoteList = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemQuoteBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel =
            QuoteViewModel()

        fun bind(quote: Quote) {
            viewModel.bind(quote)
            binding.viewModel = viewModel
        }
    }
}