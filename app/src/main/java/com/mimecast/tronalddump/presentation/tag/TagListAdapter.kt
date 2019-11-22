package com.mimecast.tronalddump.presentation.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mimecast.tronalddump.R
import com.mimecast.tronalddump.databinding.ItemTagBinding
import com.mimecast.tronalddump.presentation.tag.viewmodel.TagViewModel

class TagListAdapter(private val listener: ItemListenerAdapter): RecyclerView.Adapter<TagListAdapter.ViewHolder>() {

    private lateinit var iTagList: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTagBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tag, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::iTagList.isInitialized) iTagList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(iTagList[position], listener)
    }

    fun updateList(tagList: List<String>) {
        iTagList = tagList
        notifyDataSetChanged()
    }

    fun getList(): List<String> {
        return iTagList
    }

    class ViewHolder(private val binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = TagViewModel()

        fun bind(tag: String, listener: ItemListenerAdapter) {
            viewModel.bind(tag)
            binding.viewModel = viewModel
            itemView.setOnClickListener { listener.onItemClick(tag) }
        }
    }

    interface ItemListenerAdapter {
        fun onItemClick(tag: String) = Unit
    }
}