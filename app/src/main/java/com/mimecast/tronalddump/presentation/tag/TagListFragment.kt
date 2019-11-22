package com.mimecast.tronalddump.presentation.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mimecast.tronalddump.MainActivity
import com.mimecast.tronalddump.R
import com.mimecast.tronalddump.databinding.FragmentTagListBinding
import com.mimecast.tronalddump.presentation.tag.viewmodel.TagListViewModel

class TagListFragment: Fragment() {

    private lateinit var iBinding: FragmentTagListBinding
    private lateinit var iViewModel: TagListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        iBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag_list, container, false)
        iViewModel = ViewModelProviders.of(this).get(TagListViewModel::class.java)
        iBinding.tagList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        iBinding.viewModel = iViewModel
        return iBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Show the tag details event
        iViewModel.showTagDetailsEvent.observe(this, Observer {
            if (it != null) {
                val anActivity = (activity as MainActivity)
                if (null != anActivity && !anActivity.isFinishing) {
                    anActivity.showTagDetails(it)
                }
            }
        })
        iViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if (null != errorMessage) showError(errorMessage) else hideError()
        })
    }

    private fun showError(errorMessage: String) {
        errorSnackbar = Snackbar.make(iBinding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, iViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
