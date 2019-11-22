package com.mimecast.tronalddump

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mimecast.tronalddump.base.AppDefine
import com.mimecast.tronalddump.databinding.ActivityMainBinding
import com.mimecast.tronalddump.presentation.tag.TagDetailsFragment
import com.mimecast.tronalddump.presentation.tag.TagListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var iBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        iBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Adding the list fragment
        // Old way of inflating fragment we should use the navGraph instead
        supportFragmentManager.beginTransaction()
            .replace(fragment_container.id,
                TagListFragment()
            ).commit()
    }

    fun showTagDetails(tag: String) {
        // Going to the details fragment where the list of quotes will be shown
        val tagDetailsFragment = TagDetailsFragment()
        val bundle = Bundle()
        bundle.putString(AppDefine.BUNDLE_KEY_TAG_STRING, tag)
        tagDetailsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(fragment_container.id, tagDetailsFragment)
            .addToBackStack(tagDetailsFragment.TAG).commit()
    }
}