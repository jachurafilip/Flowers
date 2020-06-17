package com.example.flowers

import android.widget.ImageView
import android.databinding.BindingAdapter


object DataBidingAdapters {

    @BindingAdapter("android:src")
    fun setImageResoruce(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}

