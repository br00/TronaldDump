package com.mimecast.tronalddump.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mimecast.tronalddump.util.extension.getParentActivity


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableAlpha")
fun setMutableAlpha(view: View, alpha: MutableLiveData<Float>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && alpha != null) {
        alpha.observe(parentActivity, Observer { value -> view.alpha = value!!})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableText")
fun setMutableTextByResource(view: TextView, resource: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && resource != null) {
        resource.observe(parentActivity, Observer { value -> view.text = parentActivity.getString(value!!)})
    }
}

@BindingAdapter("mutableSrc")
fun setMutableSrc(view: ImageView, resource: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && resource != null) {
        resource.observe(parentActivity, Observer { value -> view.setImageResource(value!!)})
    }
}

@BindingAdapter("mutableDrawableTop")
fun setMutableDrawableTop(view: Button, resource: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && resource != null) {
        resource.observe(parentActivity, Observer { value -> view.setCompoundDrawablesWithIntrinsicBounds(0, value!!, 0, 0)})
    }
}


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}
