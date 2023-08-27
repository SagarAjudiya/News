package com.simform.news.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.simform.news.R

@BindingAdapter("imageFromInt")
fun ImageView.imageFromInt(image: Int) {
    setImageResource(image)
}

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url: String?) {
    Glide.with(this.context).load(url)
        .placeholder(R.drawable.news_place)
        .into(this)
}

