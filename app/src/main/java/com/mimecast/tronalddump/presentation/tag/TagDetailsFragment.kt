package com.mimecast.tronalddump.presentation.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mimecast.tronalddump.R
import com.mimecast.tronalddump.base.AppDefine
import com.mimecast.tronalddump.databinding.FragmentTagDetailsBinding
import com.mimecast.tronalddump.presentation.tag.viewmodel.TagDetailsViewModel

class TagDetailsFragment: Fragment() {

    val TAG = "TagDetailsFragment"

    private lateinit var iBinding: FragmentTagDetailsBinding
    private lateinit var iViewModel: TagDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        iBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag_details, container, false)
        iViewModel = ViewModelProviders.of(this).get(TagDetailsViewModel::class.java)
        iBinding.quoteList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val tag = arguments?.getString(AppDefine.BUNDLE_KEY_TAG_STRING)
        iViewModel.bind(tag!!)

        iBinding.viewModel = iViewModel
        return iBinding.root
    }

}
